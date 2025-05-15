package com.example.myNutrition.infra.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.myNutrition.infra.s3.exception.ImageTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile multipartFile) {
        validateImageExtension(multipartFile);

        String fileName = createUniqueFileName(multipartFile.getOriginalFilename());
        try {
            amazonS3Client.putObject(bucket, fileName, multipartFile.getInputStream(), null);
        } catch (IOException e) {
            throw new RuntimeException("S3 파일 업로드 실패", e);
        }

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void validateImageExtension(MultipartFile file) {
        List<String> validExtensions = List.of("jpg", "jpeg", "png", "gif");
        String ext = getExtension(file.getOriginalFilename());
        if (!validExtensions.contains(ext.toLowerCase())) {
            throw new ImageTypeException("jpg/jpeg/png/gif만 업로드 가능합니다.");
        }
    }

    private String createUniqueFileName(String originalName) {
        return UUID.randomUUID() + "." + getExtension(originalName);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}