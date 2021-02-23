package com.learning.portal.core.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.learning.portal.web.config.entity.AmazonConfig;
import com.learning.portal.web.config.repository.AmazonConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class AmazonService implements AmazonServiceInterface {

  @Autowired private AmazonConfigRepository amazonConfigRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(AmazonService.class);

  @Override
  public String uploadMultipartFile(MultipartFile file, String fileName) throws IOException {
    String directory = System.getProperty("java.io.tmpdir");
    String filepath = Paths.get(directory, fileName).toString();

    try (BufferedOutputStream stream =
        new BufferedOutputStream(new FileOutputStream(new File(filepath)))) {
      stream.write(file.getBytes());
    }
    File uploadFile = new File(filepath);
    return uploadFile(uploadFile, fileName);
  }

  private String uploadFile(File file, String fileName) {
    AmazonConfig amazonConfig = amazonConfigRepository.findByName(AmazonConfig.s3AWS).get();
    BasicAWSCredentials awsCreds =
        new BasicAWSCredentials(amazonConfig.getAccessKey(), amazonConfig.getSecretKey());
    AmazonS3 s3Client = new AmazonS3Client(awsCreds);

    try {
      LOGGER.info("Uploading file to S3 Bucket");
      LOGGER.info("==================================================================");
      // Upload file
      if (s3Client.doesBucketExist(amazonConfig.getBucketName())) {
        LOGGER.info("Bucket was found");
        s3Client.putObject(
            new PutObjectRequest(amazonConfig.getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        LOGGER.info("==================================================================");
      }
    } catch (AmazonServiceException ase) {
      ase.printStackTrace();
      LOGGER.error("Request made it to Amazon Web Service but returned with an error:");
      LOGGER.error("==================================================================");
      LOGGER.error("Error Message:    " + ase.getMessage());
      LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
      LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
      LOGGER.error("Error Type:       " + ase.getErrorType());
      LOGGER.error("Request ID:       " + ase.getRequestId());
      LOGGER.error("==================================================================");

    } catch (AmazonClientException ace) {
      ace.printStackTrace();
      LOGGER.error(
          "The client encountered an internal server error while performing this request:");
      LOGGER.error("==================================================================");
      LOGGER.error("Error Message: " + ace.getMessage());
      LOGGER.error("==================================================================");
    }
    // Generate file url
    if (!StringUtils.isEmpty(fileName)) {
      UriComponents uriComponents =
          UriComponentsBuilder.newInstance()
              .scheme("https")
              .host(amazonConfig.getS3Url())
              .path("/" + amazonConfig.getBucketName().substring(9) + "/" + fileName)
              .build()
              .encode();
      return uriComponents.toString();
    }
    return "";
  }

  @Override
  public boolean deleteFile(String filename) {
    boolean deleted = false;
    AmazonConfig amazonConfig = amazonConfigRepository.findByName(AmazonConfig.s3AWS).get();

    BasicAWSCredentials awsCreds =
        new BasicAWSCredentials(amazonConfig.getAccessKey(), amazonConfig.getSecretKey());
    AmazonS3 s3Client = new AmazonS3Client(awsCreds);
    try {
      // Delete file
      if (s3Client.doesBucketExistV2(amazonConfig.getBucketName())) {
        LOGGER.info("==================================================================");
        LOGGER.info("Deleting profile pic");
        boolean exists = s3Client.doesObjectExist(amazonConfig.getBucketName(), filename);
        if (exists)
          s3Client.deleteObject(new DeleteObjectRequest(amazonConfig.getBucketName(), filename));
        deleted = true;
        LOGGER.info("==================================================================");
      }
    } catch (AmazonServiceException ase) {
      ase.printStackTrace();
      LOGGER.error("Request made it to Amazon Web Service but returned with an error:");
      LOGGER.error("==================================================================");
      LOGGER.error("Error Message:    " + ase.getMessage());
      LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
      LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
      LOGGER.error("Error Type:       " + ase.getErrorType());
      LOGGER.error("Request ID:       " + ase.getRequestId());
      LOGGER.error("==================================================================");

    } catch (AmazonClientException ace) {
      ace.printStackTrace();
      LOGGER.error(
          "The client encountered an internal server error while performing this request:");
      LOGGER.error("==================================================================");
      LOGGER.error("Error Message: " + ace.getMessage());
      LOGGER.error("==================================================================");
    }
    return deleted;
  }
}
