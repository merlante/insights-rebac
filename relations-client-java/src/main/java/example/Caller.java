package example;

import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import api.relations.v1.*;
import client.RelationsGrpcClientsManager;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.function.Consumer;

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

        /* Non-blocking (with callbacks rather the StreamObserver)
         * (onCompleted() not an argument. Use StreamObserver if you care about that.)
         */
        Consumer<CheckResponse> onNext = (cr) -> {
            var p = cr.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;
        };
        checkClient.check(checkRequest, onNext, t -> {}); // throwable not handled yet in the case of error

    }

    public static void getRelationshipsExample() {
        var url = "localhost:8080";

        /* Make a secure connection with grpc TLS this time */
        var clientsManager = RelationsGrpcClientsManager.forSecureClients(url);
        var relationTuplesClient = clientsManager.getRelationTuplesClient();

        var roleBindingsOnWorkspaceFilter = RelationshipFilter.newBuilder()
                .setObjectType("workspace")
                .setObjectId("my-lovely-workspace")
                .setRelation("user_grant").build();
        var readRelationshipsRequest = ReadRelationshipsRequest.newBuilder()
                .setFilter(roleBindingsOnWorkspaceFilter).build();

        /*
         * Blocking
         */

        var response = relationTuplesClient.readRelationships(readRelationshipsRequest);

        /* We could make this a stream rather than a list -- which is implied/inferred from
         * the proto -- but for the blocking call there is obviously no concurrency benefit.
         * List may also change, because I think we need a proto spec change here to specify a stream.
         * */
        List<Relationship> relationships = response.getRelationshipsList();

        /*
         * Non-blocking
         */

        var streamObserver = new StreamObserver<ReadRelationshipsResponse>() {
            @Override
            public void onNext(ReadRelationshipsResponse response) {
                /* Because we don't return a stream, but a response object with all the relationships inside,
                 * we get no benefit from an async/non-blocking call right now. It all returns at once.
                 */
                List<Relationship> relationships = response.getRelationshipsList();
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
        relationTuplesClient.readRelationships(readRelationshipsRequest, streamObserver);

    }

}
