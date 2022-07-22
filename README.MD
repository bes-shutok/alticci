# Simple REST Service, returning a value from the Alticci sequence

## Instructions
Commands to set up Docker image:

`docker build -f src/main/resources/Dockerfile -t example/alticci .`

`docker run -p 8183:8080 example/alticci`

After docker image is built and running, you can run the tests with:
`localhost:8183/alticci`