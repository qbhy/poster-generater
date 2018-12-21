package config

type Line struct {
	StartX int    `json:"startX"`
	StartY int    `json:"startY"`
	EndX   int    `json:"endX"`
	EndY   int    `json:"endY"`
	ZIndex int    `json:"zIndex"`
	Width  int    `json:"width"`
	Color  string `json:"color"`
}

func (line *Line) GetZIndex() int {
	return line.ZIndex;
}