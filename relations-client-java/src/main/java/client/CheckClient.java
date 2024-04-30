package client;

import api.check.v1.CheckGrpc;
import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class CheckClient {
    private static final Logger logger = Logger.getLogger(CheckClient.class.getName());

    private final CheckGrpc.CheckStub asyncStub;
    private final CheckGrpc.CheckBlockingStub blockingStub;

    CheckClient(Channel channel) {
        asyncStub = CheckGrpc.newStub(channel);
        blockingStub = CheckGrpc.newBlockingStub(channel);
    }

    public void check(CheckRequest request,
                      StreamObserver<CheckResponse> responseObserver) {
        asyncStub.check(request, responseObserver);
    }

    public CheckResponse check(CheckRequest request) {
        return blockingStub.check(request);
    }
}