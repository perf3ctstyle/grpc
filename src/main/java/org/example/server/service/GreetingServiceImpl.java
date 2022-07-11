package org.example.server.service;

import io.grpc.stub.StreamObserver;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.GreetingServiceOuterClass;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  @Override
  public void greeting(
      GreetingServiceOuterClass.HelloRequest request,
      StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
    for (int i=0; i<10000; i++) {
      try {
        Thread.sleep(100L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.
          HelloResponse.newBuilder()
          .setGreeting("Hello from server, " + request.getName())
          .build();

      responseObserver.onNext(response);
    }

    responseObserver.onCompleted();
  }
}
