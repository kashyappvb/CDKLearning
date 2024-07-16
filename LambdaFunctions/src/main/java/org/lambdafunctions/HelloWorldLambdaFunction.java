package org.lambdafunctions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloWorldLambdaFunction implements RequestHandler<String,String> {

    @Override
    public String handleRequest(String input, Context context) {
        LambdaLogger lambdaLogger = context.getLogger();
        String location = System.getenv("LOCATION");
        lambdaLogger.log("Location from env : " + location);
        return "Hello " + input + " from " + location;
    }
}