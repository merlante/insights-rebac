package client;

import api.check.v1.CheckGrpc;
import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;

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

    public Uni<CheckResponse> checkUni(CheckRequest request) {
        final UnicastProcessor<CheckResponse> responseProcessor = UnicastProcessor.create();

        var streamObserver = new StreamObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse response) {
                responseProcessor.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                responseProcessor.onError(t);
            }

            @Override
            public void onCompleted() {
                responseProcessor.onComplete();
            }
        };

        var uni = Uni.createFrom().publisher(responseProcessor);
        check(request, streamObserver);

        return uni;
    }

    public CheckResponse check(CheckRequest request) {
        return blockingStub.check(request);
    }
}