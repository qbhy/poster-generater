package config

import "github.com/fogleman/gg"

type Text struct {
	X          int     `json:"x"`
	Y          int     `json:"y"`
	Text       string  `json:"text"`
	Width      float64 `json:"width"`
	FontSize   int     `json:"fontSize"`
	Color      string  `json:"color"`
	Opacity    int     `json:"opacity"`
	ZIndex     int     `json:"zIndex"`
	LineHeight int     `json:"lineHeight"`
	TextAlign  string  `json:"textAlign"`
}

const TextAlignCenter = "center"

func (t *Text) DrawX(w float64) float64 {
	if t.TextAlign == TextAlignCenter {
		return float64(t.X) - w/2
	}
	return float64(t.X);
}

func (text Text) GetZIndex() int {
	return text.ZIndex;
}

func (text Text) Draw(dc *gg.Context) {

	// 是否已经加载过字体
	//var loadedFont = false
	//var preFontSize = 0
	//// 画字
	//if loadedFont == false {
	//	loadedFont = true
	//	preFontSize = text.FontSize
	//} else if preFontSize != text.FontSize {
	//	_ = dc.LoadFontFace(CurrentDir+"/pingfangsr.ttf", float64(text.FontSize))
	//	preFontSize = text.FontSize
	//}
	
	_ = dc.LoadFontFace(CurrentDir+"/pingfangsr.ttf", float64(text.FontSize))
	dc.SetHexColor(text.Color)
	w, _ := dc.MeasureString(text.Text)
	words := dc.WordWrap(text.Text, text.Width)
	for index, word := range words {
		dc.DrawString(word, text.DrawX(w), float64(text.Y+text.LineHeight*index))
	}

}
