# Simple REST Service, returning a value from the Alticci sequence

## Instructions
### Setting up correct Java version
This project uses Java 17 with toolchain.

If Java 17 is the default one for the system, one can skip the toolchain configuration
by setting field <activeByDefault> to `false` in `pom.xml:140`

Otherwise, one needs to configure the toolchain in the 
${user.home}/.m2/toolchains.xml file:

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
This project uses Java 17 from maven folder ${user.home}/.m2/toolchains.xml

Below is an example of its contents:
```bash
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
`localhost:8183` in a browser.

### To stop and remove the docker container:
type 'Ctrl' (or 'Command' for Macs) + 'C' in the terminal