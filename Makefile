# 需要和 pom.xml、Dockerfile 同步
VERSION=2.5

install:
	mvn install

pack:
	mvn package -e

build: pack
	docker build -t poster-server:$(VERSION) .

docker-run: build
	docker stop `docker ps -a -q -f name=poster-server`
	docker rm `docker ps -a -q -f name=poster-server`
	docker run -p 8000:8000 -d --name=poster-server poster-server:$(VERSION)
