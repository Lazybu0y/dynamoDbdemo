package com.lazybu0y;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.lazybu0y.entities.FileScanResult;
import com.lazybu0y.entities.RepoScanResult;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.nio.*;

public class Main {
    public static void main(String[] args) {
        loadProperty();
//        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000","US_EAST_1"))
//                .build();
//
//        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
//        RepoScanResult repoScanResultSchema = new RepoScanResult();
//        //repoScanResultSchema.setScanId("A");
//        FileScanResult fileScanResult = new FileScanResult();
//        fileScanResult.setFileName("application.json");
//        fileScanResult.setLineNum("102");
//        fileScanResult.setSuspiciousKey("65r%123%iuyh");
//        repoScanResultSchema.setRepoName("ABCRepo");
//        repoScanResultSchema.setFileScanResult(fileScanResult);
//        dynamoDBMapper.save(repoScanResultSchema);
        //RepoScanResult result = dynamoDBMapper.load(RepoScanResult.class,repoScanResultSchema.getScanId(),repoScanResultSchema.getRepoName());
    }

    private static void loadProperty() {
        Properties defaultProperties = new Properties();
       // Path path = Paths.get("/Users/santoshsingh/Projects/DynamoDbAndSpringIntegration/dynamoDbdemo/src/main/resources/config.properties");
        Path path = Paths.get("config.properties");
        System.out.println(path.toAbsolutePath());
        try (BufferedReader bufferedReader = Files.newBufferedReader(path.getFileName())){
            System.out.println(bufferedReader.readLine());
            defaultProperties.load(bufferedReader);
            System.out.println(defaultProperties.entrySet().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}