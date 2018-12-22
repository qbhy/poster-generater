package config

import "github.com/fogleman/gg"

type Drawable interface {
	GetZIndex() int
	Draw(dc *gg.Context)
}
