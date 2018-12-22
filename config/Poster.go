package config

import (
	"github.com/fogleman/gg"
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
	queue           DrawQueue
	sort            []int
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
	poster.initQueue()

	// 初始化画布
	dc := gg.NewContext(int(poster.Width), int(poster.Height))
	dc.SetHexColor(poster.BackgroundColor)
	dc.DrawRectangle(0, 0, poster.Width, poster.Height)
	dc.Fill()

	for _, index := range poster.sort {
		for _, drawable := range poster.queue[index] {
			drawable.Draw(dc)
		}
	}

	err := dc.SavePNG(ImgTempDir + posterFileName)
	if err != nil {
		return err
	}

	return nil
}

func (poster *Poster) initQueue() {
	var queue DrawQueue
	sort := []int{}

	if poster.queue == nil {
		queue = make(DrawQueue)
	} else {
		queue = poster.queue
	}

	for _, drawable := range poster.Images {
		queue, sort = appendToQueue(queue, drawable, sort)
	}

	for _, drawable := range poster.Lines {
		queue, sort = appendToQueue(queue, drawable, sort)
	}

	for _, drawable := range poster.Blocks {
		queue, sort = appendToQueue(queue, drawable, sort)
	}

	for _, drawable := range poster.Texts {
		queue, sort = appendToQueue(queue, drawable, sort)
	}

	poster.sort = sort
	utils2.QuickSort(poster.sort)
	poster.queue = queue
}

func appendToQueue(queue DrawQueue, drawable Drawable, sort []int) (DrawQueue, []int) {
	index := drawable.GetZIndex()
	value, exists := queue[index]
	if exists {
		queue[index] = append(value, drawable)
	} else {
		sort = append(sort, index)
		queue[index] = []Drawable{drawable}
	}

	return queue, sort
}
