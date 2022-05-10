package com.lazybu0y;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.lazybu0y.entities.FileScanResult;
import com.lazybu0y.entities.RepoScanResult;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000","US_EAST_1"))
                .build();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        RepoScanResult repoScanResultSchema = new RepoScanResult();
        //repoScanResultSchema.setScanId("A");
        FileScanResult fileScanResult = new FileScanResult();
        fileScanResult.setFileName("application.json");
        fileScanResult.setLineNum("102");
        fileScanResult.setSuspiciousKey("65r%123%iuyh");
        repoScanResultSchema.setRepoName("ABCRepo");
        repoScanResultSchema.setFileScanResult(fileScanResult);
        dynamoDBMapper.save(repoScanResultSchema);
        //RepoScanResult result = dynamoDBMapper.load(RepoScanResult.class,repoScanResultSchema.getScanId(),repoScanResultSchema.getRepoName());
    }
}