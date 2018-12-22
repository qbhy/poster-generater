package config

import (
	"fmt"
	"github.com/fogleman/gg"
	"github.com/nfnt/resize"
	"github.com/qbhy/go-utils"
	utils2 "github.com/qbhy/poster-generater/utils"
)

type Image struct {
	X            int    `json:"x"`
	Y            int    `json:"y"`
	Url          string `json:"url"`
	Width        int    `json:"width"`
	Height       int    `json:"height"`
	BorderRadius int    `json:"borderRadius"`
	ZIndex       int    `json:"zIndex"`
}

func (img Image) Ax() float64 {
	return float64(img.X) - float64(img.Width)*0.5;
}

func (img Image) Ay() float64 {
	return float64(img.Y) - float64(img.Height)*0.5;
}

func (img Image) GetZIndex() int {
	return img.ZIndex;
}

func (img Image) Draw(dc *gg.Context)  {
	var filename = utils.Md5(img.Url);
	imgPath := ImgTempDir + filename
	if exists, _ := utils.PathExists(imgPath); !exists {
		utils.DownloadFile(img.Url, ImgTempDir, filename)
	}

	if imgInstance, err := gg.LoadImage(imgPath); err == nil {

		imgInstance = resize.Resize(uint(img.Width), uint(img.Height), imgInstance, resize.Lanczos3)

		if img.BorderRadius > 0 {
			imgInstance = utils2.Circle(imgInstance)
		}

		dc.DrawImage(imgInstance, img.X, img.Y)
	} else {
		fmt.Println("image url:", img.Url)
	}
}
