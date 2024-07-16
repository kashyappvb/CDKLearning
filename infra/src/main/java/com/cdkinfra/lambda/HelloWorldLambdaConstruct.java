package com.cdkinfra.lambda;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

import java.util.Map;

public class HelloWorldLambdaConstruct extends Construct {

    public HelloWorldLambdaConstruct(@NotNull Construct scope, @NotNull String id) {
        super(scope, id);

        Function.Builder
                .create(scope, "hello-world-lambda-function-from-cdk")
                .runtime(Runtime.JAVA_11)
                .functionName("HelloWorldLambdaFromCDK")
                .architecture(Architecture.X86_64)
                .memorySize(128)
                .environment(Map.of("LOCATION", "Mysore"))
                .code(Code.fromAsset("../assets/function.jar"))
                .handler("org.lambdafunctions.HelloWorldLambdaFunction")
                .build();
    }
}
