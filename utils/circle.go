package utils

import (
	"image"
	"image/color"
	"math"
)

func Circle(srcImg image.Image) image.Image {

	w := srcImg.Bounds().Max.X - srcImg.Bounds().Min.X
	h := srcImg.Bounds().Max.Y - srcImg.Bounds().Min.Y

	d := w
	if w > h {
		d = h
	}

	dstImg := NewCircleMask(srcImg, image.Point{0, 0}, d)
	return dstImg
}

func NewCircleMask(img image.Image, p image.Point, d int) CircleMask {
	return CircleMask{img, p, d}
}

type CircleMask struct {
	image    image.Image
	point    image.Point
	diameter int
}

func (ci CircleMask) ColorModel() color.Model {
	return ci.image.ColorModel()
}

func (ci CircleMask) Bounds() image.Rectangle {
	return image.Rect(0, 0, ci.diameter, ci.diameter)
}

func (ci CircleMask) At(x, y int) color.Color {
	d := ci.diameter
	dis := math.Sqrt(math.Pow(float64(x-d/2), 2) + math.Pow(float64(y-d/2), 2))
	if dis > float64(d)/2 {
		return ci.image.ColorModel().Convert(color.RGBA{255, 255, 255, 0})
	} else {
		return ci.image.At(ci.point.X+x, ci.point.Y+y)
	}
}
