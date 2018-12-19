package main

import (
	"encoding/json"
	"fmt"
	"github.com/fogleman/gg"
	"github.com/gin-gonic/gin"
	"github.com/nfnt/resize"
	"github.com/qbhy/go-utils"
	"github.com/qbhy/poster-generater/config"
	utils2 "github.com/qbhy/poster-generater/utils"
	"net/http"
	"os"
)

func main() {
	r := gin.Default()

	assetsPrefix := "/assets/"
	r.Static(assetsPrefix, "./temp")
	currentDir := utils.CurrentPath()
	imgTempDir := currentDir + "temp/"

	r.POST("poster", func(c *gin.Context) {

		var imgConf config.Config
		if err := c.ShouldBindJSON(&imgConf); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		jsonBytes, err := json.Marshal(imgConf)

		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		posterFileName := utils.Md5(string(jsonBytes)) + ".png"

		if exists, _ := utils.PathExists(imgTempDir + posterFileName); exists {
			c.JSON(200, gin.H{"url": c.Request.Host + assetsPrefix + posterFileName})
			return
		}

		dc := gg.NewContext(imgConf.Width, imgConf.Height)
		dc.SetHexColor(imgConf.BackgroundColor)
		dc.DrawRectangle(0, 0, float64(imgConf.Width), float64(imgConf.Height))
		dc.Fill()

		// 画框框
		for _, block := range imgConf.Blocks {
			dc.DrawRectangle(float64(block.X), float64(block.Y), float64(block.Width), float64(block.Height))
			if block.BackgroundColor != "" {
				dc.SetHexColor(block.BackgroundColor)
				dc.Fill()
			}
			if block.BorderColor != "" {
				dc.SetHexColor(block.BorderColor)
				dc.Stroke()
			}
		}

		// 画图片
		for _, drawImg := range imgConf.Images {
			var filename = utils.Md5(drawImg.Url);
			imgPath := imgTempDir + filename
			if exists, _ := utils.PathExists(imgPath); !exists {
				utils.DownloadFile(drawImg.Url, imgTempDir, filename)
			}

			if imgInstance, err := gg.LoadImage(imgPath); err == nil {

				imgInstance = resize.Resize(uint(drawImg.Width), uint(drawImg.Height), imgInstance, resize.Lanczos3)

				if drawImg.BorderRadius > 0 {
					imgInstance = utils2.Circle(imgInstance)
				}

				dc.DrawImage(imgInstance, drawImg.X, drawImg.Y)
			} else {
				fmt.Println("image url:", drawImg.Url)
			}
		}

		if len(imgConf.Texts) > 0 {

			// 是否已经加载过字体
			var loadedFont = false
			var preFontSize = 0

			// 画字
			for _, drawText := range imgConf.Texts {

				if loadedFont == false {
					_ = dc.LoadFontFace(currentDir+"/pingfangsr.ttf", float64(drawText.FontSize))
					loadedFont = true
					preFontSize = drawText.FontSize
				} else if preFontSize != drawText.FontSize {
					_ = dc.LoadFontFace(currentDir+"/pingfangsr.ttf", float64(drawText.FontSize))
					preFontSize = drawText.FontSize
				}

				dc.SetHexColor(drawText.Color)
				w, _ := dc.MeasureString(drawText.Text)
				words := dc.WordWrap(drawText.Text, drawText.Width)
				for index, word := range words {
					dc.DrawString(word, drawText.DrawX(w), float64(drawText.Y+drawText.LineHeight*index))
				}
			}
		}

		err = dc.SavePNG(imgTempDir + posterFileName)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		c.JSON(200, gin.H{"url": c.Request.Host + assetsPrefix + posterFileName})
		return
	})

	var port = "7877"
	if os.Args[1] != "" {
		port = os.Args[1]
	}

	err := r.Run(":" + port)
	if err != nil {
		panic(err);
	}
}
