package com.learning.portal.core.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AmazonServiceInterface {
    public String uploadMultipartFile(MultipartFile file, String fileName) throws IOException;

    public boolean deleteFile(String filename);
}
