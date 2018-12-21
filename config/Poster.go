package config

import (
	"fmt"
	"github.com/fogleman/gg"
	"github.com/nfnt/resize"
	"github.com/qbhy/go-utils"
	utils2 "github.com/qbhy/poster-generater/utils"
)

type Poster struct {
	Width           float64 `json:"width"`
	Height          float64 `json:"height"`
	BackgroundColor string  `json:"backgroundColor"`
	Texts           []Text  `json:"texts"`
	Images          []Image `json:"images"`
	Blocks          []Block `json:"blocks"`
	Lines           []Line  `json:"lines"`
}

const AssetsPrefix = "/assets/"

var CurrentDir string
var ImgTempDir string

func init() {
	CurrentDir = utils.CurrentPath()
	ImgTempDir = CurrentDir + "temp/"
}

type DrawQueue map[int][]Drawable

func (poster *Poster) Draw(posterFileName string) error {

	// 初始化画布
	dc := gg.NewContext(int(poster.Width), int(poster.Height))
	dc.SetHexColor(poster.BackgroundColor)
	dc.DrawRectangle(0, 0, poster.Width, poster.Height)
	dc.Fill()

	// 画框框
	for _, block := range poster.Blocks {
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
	for _, drawImg := range poster.Images {
		var filename = utils.Md5(drawImg.Url);
		imgPath := ImgTempDir + filename
		if exists, _ := utils.PathExists(imgPath); !exists {
			utils.DownloadFile(drawImg.Url, ImgTempDir, filename)
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

	if len(poster.Texts) > 0 {

		// 是否已经加载过字体
		var loadedFont = false
		var preFontSize = 0

		// 画字
		for _, drawText := range poster.Texts {

			if loadedFont == false {
				_ = dc.LoadFontFace(CurrentDir+"/pingfangsr.ttf", float64(drawText.FontSize))
				loadedFont = true
				preFontSize = drawText.FontSize
			} else if preFontSize != drawText.FontSize {
				_ = dc.LoadFontFace(CurrentDir+"/pingfangsr.ttf", float64(drawText.FontSize))
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

	err := dc.SavePNG(ImgTempDir + posterFileName)
	if err != nil {
		return err
	}

	return nil
}

func (poster *Poster) initQueue() DrawQueue {
	queue := DrawQueue{}

	return queue
}
