package org.jugistanbul.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel.*;

/**
 * @author hakdogan (huseyin.akdogan@patikaglobal.com)
 * Created on 26.03.2022
 ***/
public class WebServer
{
    private static final String ROOT_PATH = "/";
    private static final String DEFAULT_FILE_PATH = "/Users/hakdogan/Desktop/screen-shots";
    private static final int DEFAULT_PORT = 8000;
    private static final int DEFAULT_BACKLOG = 10;

    public static void main(String[] args) throws IOException {

        var config = getConfig(args);
        var filter = SimpleFileServer.createOutputFilter(System.out, config.getOutputLevel());
        var handler = SimpleFileServer
                .createFileHandler(Path.of(config.getFilePath()));

        var server = HttpServer.create(new InetSocketAddress(config.getPort()),
                config.getBacklog(), ROOT_PATH, handler, filter);

        server.start();
        System.out.println("The server running on " + server.getAddress());
    }

    private static Config getConfig(final String[] args){

        return switch (args.length){
            case 0 -> new Config()
                        .setPort(DEFAULT_PORT)
                        .setBacklog(DEFAULT_BACKLOG)
                        .setFilePath(DEFAULT_FILE_PATH)
                        .setOutputLevel(INFO);
            case 1 -> new Config()
                        .setPort(Integer.parseInt(args[0]))
                        .setBacklog(DEFAULT_BACKLOG)
                        .setFilePath(DEFAULT_FILE_PATH)
                        .setOutputLevel(INFO);
            case 2 -> new Config()
                        .setPort(Integer.parseInt(args[0]))
                        .setBacklog(Integer.parseInt(args[1]))
                        .setFilePath(DEFAULT_FILE_PATH)
                        .setOutputLevel(INFO);
            case 3 -> new Config()
                        .setPort(Integer.parseInt(args[0]))
                        .setBacklog(Integer.parseInt(args[1]))
                        .setFilePath(args[2])
                        .setOutputLevel(INFO);
            case 4 -> new Config()
                        .setPort(Integer.parseInt(args[0]))
                        .setBacklog(Integer.parseInt(args[1]))
                        .setFilePath(args[2])
                        .setOutputLevel(getOutputLevel(args[3]));

            default -> throw new RuntimeException("Unexpected parameter");
        };
    }

    private static SimpleFileServer.OutputLevel getOutputLevel(final String level){
        return level.equals(VERBOSE.toString()) ? VERBOSE : INFO;
    }
}

class Config
{
    private int port;
    private int backlog;
    private String filePath;
    private SimpleFileServer.OutputLevel outputLevel;

    public int getPort() {
        return port;
    }

    public Config setPort(int port) {
        this.port = port;
        return this;
    }

    public int getBacklog() {
        return backlog;
    }

    public Config setBacklog(int backlog) {
        this.backlog = backlog;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Config setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public SimpleFileServer.OutputLevel getOutputLevel() {
        return outputLevel;
    }

    public Config setOutputLevel(SimpleFileServer.OutputLevel outputLevel) {
        this.outputLevel = outputLevel;
        return this;
    }
}
