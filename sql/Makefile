.PHONY: all build

IMAGE=postgres:9.6.1

all: build

build:
	@docker pull ${IMAGE}

run: build
	@docker run --rm -p 5432:5432 -v $$PWD/init:/docker-entrypoint-initdb.d --name postgres ${IMAGE}
