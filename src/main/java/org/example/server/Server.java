package org.example.server;

import io.grpc.ServerBuilder;
import java.io.IOException;
import org.example.server.service.GreetingServiceImpl;

public class Server {

  public static void main(String[] args) throws IOException, InterruptedException {
    io.grpc.Server server = ServerBuilder.forPort(8080).addService(new GreetingServiceImpl()).build();
    server.start();
    System.out.println("Server started");
    server.awaitTermination();
  }
}
