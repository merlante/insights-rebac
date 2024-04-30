package client;

import api.relations.v1.RelationshipsGrpc;
import api.relations.v1.CreateRelationshipsRequest;
import api.relations.v1.ReadRelationshipsRequest;
import api.relations.v1.DeleteRelationshipsRequest;
import api.relations.v1.CreateRelationshipsResponse;
import api.relations.v1.ReadRelationshipsResponse;
import api.relations.v1.DeleteRelationshipsResponse;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;


public class RelationTuplesClient {
    private final RelationshipsGrpc.RelationshipsStub asyncStub;
    private final RelationshipsGrpc.RelationshipsBlockingStub blockingStub;

    RelationTuplesClient(Channel channel) {
        asyncStub = RelationshipsGrpc.newStub(channel);
        blockingStub = RelationshipsGrpc.newBlockingStub(channel);
    }

    /**
     */
    public void createRelationships(CreateRelationshipsRequest request,
                                    StreamObserver<CreateRelationshipsResponse> responseObserver) {
        asyncStub.createRelationships(request, responseObserver);
    }

    /**
     */
    public void readRelationships(ReadRelationshipsRequest request,
                                  StreamObserver<ReadRelationshipsResponse> responseObserver) {
        asyncStub.readRelationships(request, responseObserver);
    }

    /**
     */
    public void deleteRelationships(DeleteRelationshipsRequest request,
                                    StreamObserver<DeleteRelationshipsResponse> responseObserver) {
        asyncStub.deleteRelationships(request, responseObserver);
    }

    /**
     */
    public CreateRelationshipsResponse createRelationships(CreateRelationshipsRequest request) {
        return blockingStub.createRelationships(request);
    }

    /**
     */
    public ReadRelationshipsResponse readRelationships(ReadRelationshipsRequest request) {
        return blockingStub.readRelationships(request);
    }

    /**
     */
    public DeleteRelationshipsResponse deleteRelationships(DeleteRelationshipsRequest request) {
        return blockingStub.deleteRelationships(request);
    }
}
