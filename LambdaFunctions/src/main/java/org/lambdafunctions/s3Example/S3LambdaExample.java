package org.lambdafunctions.s3Example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class S3LambdaExample implements RequestHandler<S3Event, String> {
    Gson gson = new Gson();
    @Override
    public String handleRequest(S3Event input, Context context) {
        LambdaLogger lambdaLogger = context.getLogger();
        lambdaLogger.log("S3 Event " + gson.toJson(input));
        Map<String,String> bucketDetails = new HashMap<>();
        input.getRecords().forEach(record -> {
            String bucket = record.getS3().getBucket().getName();
            String key = record.getS3().getObject().getKey();
            bucketDetails.put(bucket,key);
        });
        lambdaLogger.log("Bucket Details " + gson.toJson(bucketDetails));
        return gson.toJson(bucketDetails);
    }
}
