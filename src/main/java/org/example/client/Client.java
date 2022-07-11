package org.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.GreetingServiceOuterClass;
import org.example.grpc.GreetingServiceOuterClass.HelloRequest;
import org.example.grpc.GreetingServiceOuterClass.HelloResponse;

public class Client {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    GreetingServiceGrpc.GreetingServiceBlockingStub stub =
        GreetingServiceGrpc.newBlockingStub(channel);

    HelloRequest request = GreetingServiceOuterClass.HelloRequest
        .newBuilder()
        .setName("Nikita")
        .addHobbies("English")
        .addHobbies("Coding")
        .build();

    Iterator<HelloResponse> response = stub.greeting(request);
    while (response.hasNext()) {
      System.out.println(response.next());
    }

    channel.shutdownNow();
  }
}
