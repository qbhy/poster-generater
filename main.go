package main

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"github.com/qbhy/go-utils"
	"github.com/qbhy/poster-generater/config"
	"net/http"
	"os"
)

func main() {
	r := gin.Default()

	r.Static(config.AssetsPrefix, "./temp")

	r.POST("poster", func(c *gin.Context) {

		var poster config.Poster
		if err := c.ShouldBindJSON(&poster); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		jsonBytes, err := json.Marshal(poster)

		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		posterFileName := utils.Md5(string(jsonBytes)) + ".png"

		if exists, _ := utils.PathExists(config.ImgTempDir + posterFileName); exists {
			c.JSON(200, gin.H{"url": c.Request.Host + config.AssetsPrefix + posterFileName})
			return
		}

		err = poster.Draw(posterFileName)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		c.JSON(200, gin.H{"url": c.Request.Host + config.AssetsPrefix + posterFileName})
		return
	})

	var port = "7877"
	if os.Args[1] != "" {
		port = os.Args[1]
	}

	err := r.Run(":" + port)
	if err != nil {
		panic(err);
	}
}
