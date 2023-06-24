FROM ubuntu:latest
LABEL authors="chait"

ENTRYPOINT ["top", "-b"]