package example;

import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import api.relations.v1.ObjectReference;
import api.relations.v1.SubjectReference;
import client.RelationsGrpcClientsManager;
import io.grpc.stub.StreamObserver;

public class Caller {

    public static void main(String[] argv) {
        var url = "localhost:8080";

        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var checkClient = clientsManager.getCheckClient();

        var checkRequest = CheckRequest.newBuilder()
                .setSubject(SubjectReference.newBuilder()
                                .setObject(ObjectReference.newBuilder()
                                        .setType("user")
                                        .setId("bob").build())
                                .build())
                .setRelation("view")
                .setObject(ObjectReference.newBuilder()
                        .setType("inventory/host")
                        .setId("host0001")
                        .build())
                .build();

        /*
         * Choice of blocking v async is made by method signature.
         * - Just CheckRequest in args and CheckResponse returned indicates blocking.
         * - StreamObserver<CheckResponse> in args indicates async.
         */

        /* Blocking */
        var checkResponse = checkClient.check(checkRequest);
        var permitted = checkResponse.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;

        /* Non-blocking
         * (Need to define and pass in 3 callbacks in a StreamObserver to handle responses.)
         */
        var streamObserver = new StreamObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse value) {
                var permitted = checkResponse.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;
            }

            @Override
            public void onError(Throwable t) {
                // TODO:
            }

            @Override
            public void onCompleted() {
                // do nothing
            }
        };
        checkClient.check(checkRequest, streamObserver);

    }
}
