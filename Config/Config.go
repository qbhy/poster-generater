package config

type Config struct {
	Width           int     `json:"width"`
	Height          int     `json:"height"`
	BackgroundColor string  `json:"backgroundColor"`
	Texts           []Text  `json:"texts"`
	Images          []Image `json:"images"`
	Blocks          []Block `json:"blocks"`
	Lines           []Line  `json:"lines"`
}
