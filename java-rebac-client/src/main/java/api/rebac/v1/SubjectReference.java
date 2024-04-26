// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rebac/v1/relationships.proto

package api.rebac.v1;

/**
 * Protobuf type {@code api.rebac.v1.SubjectReference}
 */
public final class SubjectReference extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:api.rebac.v1.SubjectReference)
    SubjectReferenceOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SubjectReference.newBuilder() to construct.
  private SubjectReference(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SubjectReference() {
    relation_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SubjectReference();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return api.rebac.v1.RelationshipsOuterClass.internal_static_api_rebac_v1_SubjectReference_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return api.rebac.v1.RelationshipsOuterClass.internal_static_api_rebac_v1_SubjectReference_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            api.rebac.v1.SubjectReference.class, api.rebac.v1.SubjectReference.Builder.class);
  }

  public static final int RELATION_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object relation_ = "";
  /**
   * <code>string relation = 1;</code>
   * @return The relation.
   */
  @java.lang.Override
  public java.lang.String getRelation() {
    java.lang.Object ref = relation_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      relation_ = s;
      return s;
    }
  }
  /**
   * <code>string relation = 1;</code>
   * @return The bytes for relation.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getRelationBytes() {
    java.lang.Object ref = relation_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      relation_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int OBJECT_FIELD_NUMBER = 2;
  private api.rebac.v1.ObjectReference object_;
  /**
   * <code>.api.rebac.v1.ObjectReference object = 2;</code>
   * @return Whether the object field is set.
   */
  @java.lang.Override
  public boolean hasObject() {
    return object_ != null;
  }
  /**
   * <code>.api.rebac.v1.ObjectReference object = 2;</code>
   * @return The object.
   */
  @java.lang.Override
  public api.rebac.v1.ObjectReference getObject() {
    return object_ == null ? api.rebac.v1.ObjectReference.getDefaultInstance() : object_;
  }
  /**
   * <code>.api.rebac.v1.ObjectReference object = 2;</code>
   */
  @java.lang.Override
  public api.rebac.v1.ObjectReferenceOrBuilder getObjectOrBuilder() {
    return object_ == null ? api.rebac.v1.ObjectReference.getDefaultInstance() : object_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(relation_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, relation_);
    }
    if (object_ != null) {
      output.writeMessage(2, getObject());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(relation_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, relation_);
    }
    if (object_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getObject());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof api.rebac.v1.SubjectReference)) {
      return super.equals(obj);
    }
    api.rebac.v1.SubjectReference other = (api.rebac.v1.SubjectReference) obj;

    if (!getRelation()
        .equals(other.getRelation())) return false;
    if (hasObject() != other.hasObject()) return false;
    if (hasObject()) {
      if (!getObject()
          .equals(other.getObject())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RELATION_FIELD_NUMBER;
    hash = (53 * hash) + getRelation().hashCode();
    if (hasObject()) {
      hash = (37 * hash) + OBJECT_FIELD_NUMBER;
      hash = (53 * hash) + getObject().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static api.rebac.v1.SubjectReference parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static api.rebac.v1.SubjectReference parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static api.rebac.v1.SubjectReference parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static api.rebac.v1.SubjectReference parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static api.rebac.v1.SubjectReference parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static api.rebac.v1.SubjectReference parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(api.rebac.v1.SubjectReference prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code api.rebac.v1.SubjectReference}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:api.rebac.v1.SubjectReference)
      api.rebac.v1.SubjectReferenceOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return api.rebac.v1.RelationshipsOuterClass.internal_static_api_rebac_v1_SubjectReference_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return api.rebac.v1.RelationshipsOuterClass.internal_static_api_rebac_v1_SubjectReference_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              api.rebac.v1.SubjectReference.class, api.rebac.v1.SubjectReference.Builder.class);
    }

    // Construct using api.rebac.v1.SubjectReference.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      relation_ = "";
      object_ = null;
      if (objectBuilder_ != null) {
        objectBuilder_.dispose();
        objectBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return api.rebac.v1.RelationshipsOuterClass.internal_static_api_rebac_v1_SubjectReference_descriptor;
    }

    @java.lang.Override
    public api.rebac.v1.SubjectReference getDefaultInstanceForType() {
      return api.rebac.v1.SubjectReference.getDefaultInstance();
    }

    @java.lang.Override
    public api.rebac.v1.SubjectReference build() {
      api.rebac.v1.SubjectReference result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public api.rebac.v1.SubjectReference buildPartial() {
      api.rebac.v1.SubjectReference result = new api.rebac.v1.SubjectReference(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(api.rebac.v1.SubjectReference result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.relation_ = relation_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.object_ = objectBuilder_ == null
            ? object_
            : objectBuilder_.build();
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof api.rebac.v1.SubjectReference) {
        return mergeFrom((api.rebac.v1.SubjectReference)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(api.rebac.v1.SubjectReference other) {
      if (other == api.rebac.v1.SubjectReference.getDefaultInstance()) return this;
      if (!other.getRelation().isEmpty()) {
        relation_ = other.relation_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (other.hasObject()) {
        mergeObject(other.getObject());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              relation_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              input.readMessage(
                  getObjectFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object relation_ = "";
    /**
     * <code>string relation = 1;</code>
     * @return The relation.
     */
    public java.lang.String getRelation() {
      java.lang.Object ref = relation_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        relation_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string relation = 1;</code>
     * @return The bytes for relation.
     */
    public com.google.protobuf.ByteString
        getRelationBytes() {
      java.lang.Object ref = relation_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        relation_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string relation = 1;</code>
     * @param value The relation to set.
     * @return This builder for chaining.
     */
    public Builder setRelation(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      relation_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string relation = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearRelation() {
      relation_ = getDefaultInstance().getRelation();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string relation = 1;</code>
     * @param value The bytes for relation to set.
     * @return This builder for chaining.
     */
    public Builder setRelationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      relation_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private api.rebac.v1.ObjectReference object_;
    private com.google.protobuf.SingleFieldBuilderV3<
        api.rebac.v1.ObjectReference, api.rebac.v1.ObjectReference.Builder, api.rebac.v1.ObjectReferenceOrBuilder> objectBuilder_;
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     * @return Whether the object field is set.
     */
    public boolean hasObject() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     * @return The object.
     */
    public api.rebac.v1.ObjectReference getObject() {
      if (objectBuilder_ == null) {
        return object_ == null ? api.rebac.v1.ObjectReference.getDefaultInstance() : object_;
      } else {
        return objectBuilder_.getMessage();
      }
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    public Builder setObject(api.rebac.v1.ObjectReference value) {
      if (objectBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        object_ = value;
      } else {
        objectBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    public Builder setObject(
        api.rebac.v1.ObjectReference.Builder builderForValue) {
      if (objectBuilder_ == null) {
        object_ = builderForValue.build();
      } else {
        objectBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    public Builder mergeObject(api.rebac.v1.ObjectReference value) {
      if (objectBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          object_ != null &&
          object_ != api.rebac.v1.ObjectReference.getDefaultInstance()) {
          getObjectBuilder().mergeFrom(value);
        } else {
          object_ = value;
        }
      } else {
        objectBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    public Builder clearObject() {
      bitField0_ = (bitField0_ & ~0x00000002);
      object_ = null;
      if (objectBuilder_ != null) {
        objectBuilder_.dispose();
        objectBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    public api.rebac.v1.ObjectReference.Builder getObjectBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getObjectFieldBuilder().getBuilder();
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    public api.rebac.v1.ObjectReferenceOrBuilder getObjectOrBuilder() {
      if (objectBuilder_ != null) {
        return objectBuilder_.getMessageOrBuilder();
      } else {
        return object_ == null ?
            api.rebac.v1.ObjectReference.getDefaultInstance() : object_;
      }
    }
    /**
     * <code>.api.rebac.v1.ObjectReference object = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        api.rebac.v1.ObjectReference, api.rebac.v1.ObjectReference.Builder, api.rebac.v1.ObjectReferenceOrBuilder> 
        getObjectFieldBuilder() {
      if (objectBuilder_ == null) {
        objectBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            api.rebac.v1.ObjectReference, api.rebac.v1.ObjectReference.Builder, api.rebac.v1.ObjectReferenceOrBuilder>(
                getObject(),
                getParentForChildren(),
                isClean());
        object_ = null;
      }
      return objectBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:api.rebac.v1.SubjectReference)
  }

  // @@protoc_insertion_point(class_scope:api.rebac.v1.SubjectReference)
  private static final api.rebac.v1.SubjectReference DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new api.rebac.v1.SubjectReference();
  }

  public static api.rebac.v1.SubjectReference getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SubjectReference>
      PARSER = new com.google.protobuf.AbstractParser<SubjectReference>() {
    @java.lang.Override
    public SubjectReference parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<SubjectReference> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SubjectReference> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public api.rebac.v1.SubjectReference getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

