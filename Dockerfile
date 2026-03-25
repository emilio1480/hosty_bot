FROM ubuntu:latest
LABEL authors="Emilio"

ENTRYPOINT ["top", "-b"]