package com.cdkinfra.lambda;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.util.Map;

public class S3ToLambdaConstruct extends Construct {

    Function function;
    public S3ToLambdaConstruct(@NotNull Construct scope, @NotNull String id) {
        super(scope, id);

        function = Function.Builder
                .create(scope, "s3-to-lambda")
                .runtime(Runtime.JAVA_11)
                .functionName("S3ToLambdaFromCDK")
                .architecture(Architecture.X86_64)
                .memorySize(128)
                .code(Code.fromAsset("../assets/function.jar"))
                .handler("org.lambdafunctions.s3Example.S3LambdaExample")
                .build();
    }

    public Function getFunction() {
        return function;
    }

}
