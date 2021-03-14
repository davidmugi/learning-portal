package com.learning.portal.web.config.entity;

import com.learning.portal.core.audit.AuditData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "amazon_config")
public class AmazonConfig extends AuditData {

    public static final String s3AWS = "S3DO";

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "access_key")
    @NotBlank(message = "Access key is required")
    private String accessKey;

    @Column(name = "secret_key")
    @NotBlank(message = "Secret key is required")
    private String secretKey;

    @Column(name = "bucket_name")
    @NotBlank(message = "Bucket name is required")
    private String bucketName;

    @Column(name = "s3Url")
    @NotBlank(message = "S3url is required")
    private String s3Url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }
}
