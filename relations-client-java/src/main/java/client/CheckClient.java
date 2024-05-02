package client;

import api.check.v1.CheckGrpc;
import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import api.relations.v1.ReadRelationshipsResponse;
import api.relations.v1.Relationship;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
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

    public void check(CheckRequest request, Consumer<CheckResponse> onNext, Consumer<Throwable> onError) {
        var streamObserver = new StreamObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse response) {
                onNext.accept(response);
            }

            @Override
            public void onError(Throwable t) {
                onError.accept(t);
            }

            @Override
            public void onCompleted() {
                // if you want this callback, maybe you should just use StreamObserver?
            }
        };

        check(request, streamObserver);
    }

    public CheckResponse check(CheckRequest request) {
        return blockingStub.check(request);
    }
}