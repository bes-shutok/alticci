# Simple REST Service, returning a value from the Alticci sequence

## Instructions
### Setting up correct Java version
This project uses Java 17 with toolchain.

If Java 17 is the default one for the system, one can skip the toolchain configuration
by setting field `<activeByDefault>` to `false` in `pom.xml:94`

Otherwise, one needs to configure the `jdkHome` path in the 
`${user.home}/.m2/toolchains.xml` file:

Below is an example of its contents:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>17</version>
            <vendor>oracle</vendor>
        </provides>
        <configuration>
            <jdkHome>C:\dev\Tools\jdk-17</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```

### Compiling the project locally
To compile the project locally, one needs to execute the following command:
```shell
mvn clean install
```
or this (to skip the tests):
```shell
mvn clean install -DskipTests
```

### Build Docker image and run the application:
Here we are using local port 8183 for the REST API.
If you already have something binding that port on your local machine,
you may change the port number below.

```shell
docker build -f src/main/resources/Dockerfile -t example/alticci .
docker run -it --rm -p 8183:8080 example/alticci
```

After docker image is built and running, you can run the tests with:
`http://localhost:8183/api/alticci/{n}` in a browser.

to see Open Api documentation one could open the following url:
`http://localhost:8183/v3/api-docs`

There is also UI to browse the APi with the following url:
`http://localhost:8183/swagger-ui/index.html`

### To stop and remove the docker container:
type 'Ctrl' (or 'Command' for Macs) + 'C' in the terminal