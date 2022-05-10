package com.lazybu0y.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class FileScanResult {
    private String fileName;
    private String lineNum;
    private String suspiciousKey;

    @DynamoDBAttribute(attributeName = "FineName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @DynamoDBAttribute(attributeName = "LineNum")
    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }
    @DynamoDBAttribute(attributeName = "SuspiciousKey")
    public String getSuspiciousKey() {
        return suspiciousKey;
    }

    public void setSuspiciousKey(String suspiciousKey) {
        this.suspiciousKey = suspiciousKey;
    }
}
