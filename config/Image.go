package config

type Image struct {
	X            int    `json:"x"`
	Y            int    `json:"y"`
	Url          string `json:"url"`
	Width        int    `json:"width"`
	Height       int    `json:"height"`
	BorderRadius int    `json:"borderRadius"`
	ZIndex       int    `json:"zIndex"`
}

func (img *Image) Ax() float64 {
	return float64(img.X) - float64(img.Width)*0.5;
}

func (img *Image) Ay() float64 {
	return float64(img.Y) - float64(img.Height)*0.5;
}

func (img *Image) GetZIndex() int {
	return img.ZIndex;
}
