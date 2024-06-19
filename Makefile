.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app

clean:
	gradle clean

build:
	gradle installDist

run:
	gradle run

lint:
	gradle checkstyleMain checkstyleTest

test:
	gradle test

report:
	gradle jacocoTestReport

build-run: build run

.PHONY: build