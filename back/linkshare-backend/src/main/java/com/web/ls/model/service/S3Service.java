package com.web.ls.model.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    @Value("${cloud.aws.s3.dirLinkdetail}")
    public String dirLinkdetail;  // S3  dir 이름

    public String uploadToLinkdetailDir(MultipartFile file) {

        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        ObjectMetadata objMeta = new ObjectMetadata();
        try {
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            objMeta.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayIs = new ByteArrayInputStream(bytes);

            amazonS3Client.putObject(new PutObjectRequest(bucket, dirLinkdetail + "/" + fileName, byteArrayIs, objMeta));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return amazonS3Client.getUrl(bucket, dirLinkdetail + fileName).toString();
    }

    public ResponseEntity<byte[]> downloadFromLinkdetailDir(String filename) throws IOException {
        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, filename));
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String storedFileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", storedFileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
