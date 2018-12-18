package config

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
