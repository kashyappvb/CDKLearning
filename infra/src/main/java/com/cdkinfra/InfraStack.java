package com.cdkinfra;

import com.cdkinfra.lambda.ByeWorldLambdaConstruct;
import com.cdkinfra.lambda.HelloWorldLambdaConstruct;
import com.cdkinfra.lambda.S3ToLambdaConstruct;
import com.cdkinfra.s3.SampleS3Bucket;
import software.amazon.awscdk.services.lambda.eventsources.S3EventSource;
import software.amazon.awscdk.services.s3.EventType;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import java.util.List;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class InfraStack extends Stack {
    public InfraStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public InfraStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "InfraQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();

        SampleS3Bucket sampleS3Bucket = new SampleS3Bucket(this, "sampleBucket");
        S3ToLambdaConstruct s3ToLambdaConstruct = new S3ToLambdaConstruct(this, "S3ToLambda");

        sampleS3Bucket.getBucket().grantRead(s3ToLambdaConstruct.getFunction());

        s3ToLambdaConstruct.getFunction().addEventSource(
                S3EventSource.Builder.create(sampleS3Bucket.getBucket())
                        .events(List.of(EventType.OBJECT_CREATED))
                        .build()
        );

        HelloWorldLambdaConstruct helloWorldLambdaConstruct = new HelloWorldLambdaConstruct(this, "hello-world-lambda-function");
        ByeWorldLambdaConstruct byeWorldLambdaConstruct = new ByeWorldLambdaConstruct(this, "bye-world-lambda-function");
        byeWorldLambdaConstruct.getNode().addDependency(helloWorldLambdaConstruct);

    }
}
