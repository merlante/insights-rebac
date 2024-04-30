// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rebac/v1/check.proto

package api.check.v1;

/**
 * Protobuf type {@code api.rebac.v1.CheckResponse}
 */
public final class CheckResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:api.rebac.v1.CheckResponse)
    CheckResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CheckResponse.newBuilder() to construct.
  private CheckResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckResponse() {
    allowed_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CheckResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return api.check.v1.CheckOuterClass.internal_static_api_rebac_v1_CheckResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return api.check.v1.CheckOuterClass.internal_static_api_rebac_v1_CheckResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            api.check.v1.CheckResponse.class, api.check.v1.CheckResponse.Builder.class);
  }

  /**
   * Protobuf enum {@code api.rebac.v1.CheckResponse.Allowed}
   */
  public enum Allowed
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>ALLOWED_UNSPECIFIED = 0;</code>
     */
    ALLOWED_UNSPECIFIED(0),
    /**
     * <code>ALLOWED_TRUE = 1;</code>
     */
    ALLOWED_TRUE(1),
    /**
     * <pre>
     * e.g.  ALLOWED_CONDITIONAL = 3;
     * </pre>
     *
     * <code>ALLOWED_FALSE = 2;</code>
     */
    ALLOWED_FALSE(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ALLOWED_UNSPECIFIED = 0;</code>
     */
    public static final int ALLOWED_UNSPECIFIED_VALUE = 0;
    /**
     * <code>ALLOWED_TRUE = 1;</code>
     */
    public static final int ALLOWED_TRUE_VALUE = 1;
    /**
     * <pre>
     * e.g.  ALLOWED_CONDITIONAL = 3;
     * </pre>
     *
     * <code>ALLOWED_FALSE = 2;</code>
     */
    public static final int ALLOWED_FALSE_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Allowed valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static Allowed forNumber(int value) {
      switch (value) {
        case 0: return ALLOWED_UNSPECIFIED;
        case 1: return ALLOWED_TRUE;
        case 2: return ALLOWED_FALSE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Allowed>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Allowed> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Allowed>() {
            public Allowed findValueByNumber(int number) {
              return Allowed.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return api.check.v1.CheckResponse.getDescriptor().getEnumTypes().get(0);
    }

    private static final Allowed[] VALUES = values();

    public static Allowed valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Allowed(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:api.rebac.v1.CheckResponse.Allowed)
  }

  public static final int ALLOWED_FIELD_NUMBER = 1;
  private int allowed_ = 0;
  /**
   * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
   * @return The enum numeric value on the wire for allowed.
   */
  @java.lang.Override public int getAllowedValue() {
    return allowed_;
  }
  /**
   * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
   * @return The allowed.
   */
  @java.lang.Override public api.check.v1.CheckResponse.Allowed getAllowed() {
    api.check.v1.CheckResponse.Allowed result = api.check.v1.CheckResponse.Allowed.forNumber(allowed_);
    return result == null ? api.check.v1.CheckResponse.Allowed.UNRECOGNIZED : result;
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
    if (allowed_ != api.check.v1.CheckResponse.Allowed.ALLOWED_UNSPECIFIED.getNumber()) {
      output.writeEnum(1, allowed_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (allowed_ != api.check.v1.CheckResponse.Allowed.ALLOWED_UNSPECIFIED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, allowed_);
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
    if (!(obj instanceof api.check.v1.CheckResponse)) {
      return super.equals(obj);
    }
    api.check.v1.CheckResponse other = (api.check.v1.CheckResponse) obj;

    if (allowed_ != other.allowed_) return false;
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
    hash = (37 * hash) + ALLOWED_FIELD_NUMBER;
    hash = (53 * hash) + allowed_;
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static api.check.v1.CheckResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static api.check.v1.CheckResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static api.check.v1.CheckResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static api.check.v1.CheckResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static api.check.v1.CheckResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static api.check.v1.CheckResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static api.check.v1.CheckResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static api.check.v1.CheckResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static api.check.v1.CheckResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static api.check.v1.CheckResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static api.check.v1.CheckResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static api.check.v1.CheckResponse parseFrom(
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
  public static Builder newBuilder(api.check.v1.CheckResponse prototype) {
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
   * Protobuf type {@code api.rebac.v1.CheckResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:api.rebac.v1.CheckResponse)
      api.check.v1.CheckResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return api.check.v1.CheckOuterClass.internal_static_api_rebac_v1_CheckResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return api.check.v1.CheckOuterClass.internal_static_api_rebac_v1_CheckResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              api.check.v1.CheckResponse.class, api.check.v1.CheckResponse.Builder.class);
    }

    // Construct using api.check.v1.CheckResponse.newBuilder()
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
      allowed_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return api.check.v1.CheckOuterClass.internal_static_api_rebac_v1_CheckResponse_descriptor;
    }

    @java.lang.Override
    public api.check.v1.CheckResponse getDefaultInstanceForType() {
      return api.check.v1.CheckResponse.getDefaultInstance();
    }

    @java.lang.Override
    public api.check.v1.CheckResponse build() {
      api.check.v1.CheckResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public api.check.v1.CheckResponse buildPartial() {
      api.check.v1.CheckResponse result = new api.check.v1.CheckResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(api.check.v1.CheckResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.allowed_ = allowed_;
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
      if (other instanceof api.check.v1.CheckResponse) {
        return mergeFrom((api.check.v1.CheckResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(api.check.v1.CheckResponse other) {
      if (other == api.check.v1.CheckResponse.getDefaultInstance()) return this;
      if (other.allowed_ != 0) {
        setAllowedValue(other.getAllowedValue());
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
            case 8: {
              allowed_ = input.readEnum();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
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

    private int allowed_ = 0;
    /**
     * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
     * @return The enum numeric value on the wire for allowed.
     */
    @java.lang.Override public int getAllowedValue() {
      return allowed_;
    }
    /**
     * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
     * @param value The enum numeric value on the wire for allowed to set.
     * @return This builder for chaining.
     */
    public Builder setAllowedValue(int value) {
      allowed_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
     * @return The allowed.
     */
    @java.lang.Override
    public api.check.v1.CheckResponse.Allowed getAllowed() {
      api.check.v1.CheckResponse.Allowed result = api.check.v1.CheckResponse.Allowed.forNumber(allowed_);
      return result == null ? api.check.v1.CheckResponse.Allowed.UNRECOGNIZED : result;
    }
    /**
     * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
     * @param value The allowed to set.
     * @return This builder for chaining.
     */
    public Builder setAllowed(api.check.v1.CheckResponse.Allowed value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000001;
      allowed_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.api.rebac.v1.CheckResponse.Allowed allowed = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAllowed() {
      bitField0_ = (bitField0_ & ~0x00000001);
      allowed_ = 0;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:api.rebac.v1.CheckResponse)
  }

  // @@protoc_insertion_point(class_scope:api.rebac.v1.CheckResponse)
  private static final api.check.v1.CheckResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new api.check.v1.CheckResponse();
  }

  public static api.check.v1.CheckResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckResponse>
      PARSER = new com.google.protobuf.AbstractParser<CheckResponse>() {
    @java.lang.Override
    public CheckResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<CheckResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CheckResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public api.check.v1.CheckResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

