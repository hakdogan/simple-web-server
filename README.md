# JAVA 18 Simple Web Server

`Java 18` offers an out-of-the-box static HTTP file server with easy setup and minimal functionality called `Simple Web Server`. This repository shows you how to use its API for programmatic creation and customization.

```java
        var config = getConfig(args);
        var filter = SimpleFileServer.createOutputFilter(System.out, config.getOutputLevel());
        var handler = SimpleFileServer
                .createFileHandler(Path.of(config.getFilePath()));

        var server = HttpServer.create(new InetSocketAddress(config.getPort()),
                config.getBacklog(), ROOT_PATH, handler, filter);

        server.start();
        System.out.println("The server running on " + server.getAddress());
```

## To run
```bash
# java WebServer.java [port] [backlog] [path] [level]  

java WebServer.java 8080 10 /somepath VERBOSE
```
