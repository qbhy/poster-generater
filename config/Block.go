package config

import "github.com/fogleman/gg"

type Block struct {
	X               int    `json:"x"`
	Y               int    `json:"y"`
	Width           int    `json:"width"`
	Height          int    `json:"height"`
	ZIndex          int    `json:"zIndex"`
	BackgroundColor string `json:"backgroundColor"`
	BorderColor     string `json:"borderColor"`
}

func (block Block) GetZIndex() int {
	return block.ZIndex;
}

func (block Block) Draw(dc *gg.Context) {
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
