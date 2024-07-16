package com.cdkinfra.s3;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketProps;
import software.constructs.Construct;

public class SampleS3Bucket extends Construct {

    Bucket bucket;
    public SampleS3Bucket(@NotNull Construct scope, @NotNull String id) {
        this(scope, id, null);
    }

    public SampleS3Bucket(final Construct scope, final String id, final BucketProps props) {
        super(scope, id);

        // Use Builder pattern to configure the bucket
        bucket = Bucket.Builder.create(this, "MySampleBucket")
                .bucketName("sample-bucket-name-for-cdk")
                .autoDeleteObjects(true)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();
    }

    public Bucket getBucket() {
        return bucket;
    }

}
