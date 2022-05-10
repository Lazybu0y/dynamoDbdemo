package com.lazybu0y.entities;

import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.Long.valueOf;

public class CreateTable {
    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "    <tableName> <key>\n\n" +
                "Where:\n" +
                "    tableName - the Amazon DynamoDB table to create (for example, Music3).\n\n" +
                "    key - the key for the Amazon DynamoDB table (for example, Artist).\n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String tableName = args[0];
        String key = args[1];
        System.out.format(
                "Creating an Amazon DynamoDB table \"%s\" with a simple primary key: \"Name\".\n",
                tableName);
        URI uri;
        try {
            uri = new URI("http://localhost:8000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Region region = Region.US_EAST_1;
       // Region region = Region.of("localhost");
        DynamoDbClient ddb = DynamoDbClient.builder()

                .region(region)
                .endpointOverride(uri)
                .build();

        String result = createTable(ddb, tableName, key);
        System.out.println("New table is "+result);
        ddb.close();
    }
    // snippet-start:[dynamodb.java2.create_table.main]
    public static String createTable(DynamoDbClient ddb, String tableName, String key) {

        DynamoDbWaiter dbWaiter = ddb.waiter();
        CreateTableRequest request = CreateTableRequest.builder()
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName(key)
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .keySchema(KeySchemaElement.builder()
                        .attributeName(key)
                        .keyType(KeyType.HASH)
                        .build())
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(valueOf(10))
                        .writeCapacityUnits(valueOf(10))
                        .build())
                .tableName(tableName)
                .build();

        String newTable ="";
        try {
            CreateTableResponse response = ddb.createTable(request);
            DescribeTableRequest tableRequest = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();

            // Wait until the Amazon DynamoDB table is created
            WaiterResponse<DescribeTableResponse> waiterResponse =  dbWaiter.waitUntilTableExists(tableRequest);
            waiterResponse.matched().response().ifPresent(System.out::println);

            newTable = response.tableDescription().tableName();
            return newTable;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return "";
    }
    // snippet-end:[dynamodb.java2.create_table.main]
}
