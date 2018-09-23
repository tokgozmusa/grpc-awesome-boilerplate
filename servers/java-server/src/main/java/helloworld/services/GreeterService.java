package helloworld.services;

import helloworld.HelloWorldServer;
import helloworldproto.GreeterGrpc;
import helloworldproto.HelloRequest;
import helloworldproto.HelloResponse;
import io.grpc.stub.StreamObserver;
import java.time.LocalDateTime;

/**
 * User: tokgozmusa Date: 26.08.2018 Time: 01:42
 **/
public class GreeterService extends GreeterGrpc.GreeterImplBase {

  @Override
  public void sayHello(HelloRequest helloRequest, StreamObserver<HelloResponse> responseObserver) {
    HelloWorldServer.logger.info("Request received:" + LocalDateTime.now());

    HelloResponse helloResponse = HelloResponse.newBuilder()
        .setMessage(sayHelloImpl(helloRequest.getName())).build();
    responseObserver.onNext(helloResponse);
    responseObserver.onCompleted();
  }

  private String sayHelloImpl(String name) {
    if (name == null) {
      return "Hello there!";
    }
    return "Hello " + name + "!";
  }
}
