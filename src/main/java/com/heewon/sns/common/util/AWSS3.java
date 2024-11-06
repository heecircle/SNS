package com.heewon.sns.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

public class AWSS3 {
	private AmazonS3 s3Client;

	private final String accessKey = System.getenv("ACCESS_KEY");
	private final String secretKey = System.getenv("SECRET_KEY");
	private final Regions clientRegion = Regions.AP_NORTHEAST_2;
	private final String bucket = System.getenv("BUCKET");

	public AWSS3() {
		createS3Client();
	}

	private static AWSS3 instance = null;

	public static AWSS3 getInstance() {
		if (instance == null) {
			return new AWSS3();
		} else {
			return instance;
		}
	}

	private void createS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		this.s3Client = AmazonS3ClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(credentials))
			.withRegion(clientRegion)
			.build();
	}

	public void upload(File file, String key) {
		uploadToS3(new PutObjectRequest(bucket, key, file));
	}

	public String upload(MultipartFile image) throws IOException {
		String originalFilename = image.getOriginalFilename(); //원본 파일 명
		String extention = originalFilename.substring(originalFilename.lastIndexOf(".")); //확장자 명

		String s3FileName =
			UUID.randomUUID().toString().substring(0, 10) + extention; //변경된 파일 명

		InputStream is = image.getInputStream();
		byte[] bytes = IOUtils.toByteArray(is); //image를 byte[]로 변환

		ObjectMetadata metadata = new ObjectMetadata(); //metadata 생성
		metadata.setContentType("image/" + extention);
		metadata.setContentLength(bytes.length);

		//S3에 요청할 때 사용할 byteInputStream 생성
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, s3FileName, image.getInputStream(), null);
			s3Client.putObject(putObjectRequest);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			byteArrayInputStream.close();
			is.close();
		}
		return s3Client.getUrl(bucket, s3FileName).toString();

	}

	public void upload(InputStream is, String key, String contentType, long contentLength) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		metadata.setContentLength(contentLength);

		uploadToS3(new PutObjectRequest(this.bucket, key, is, new ObjectMetadata()));

	}

	private void uploadToS3(PutObjectRequest putObjectRequest) {
		try {
			this.s3Client.putObject(putObjectRequest);
			System.out.println(putObjectRequest.getKey());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void copy(String orgKey, String copyKey) {
		try {
			//Copy 객체 생성
			CopyObjectRequest copyObjRequest = new CopyObjectRequest(
				this.bucket,
				orgKey,
				this.bucket,
				copyKey
			);
			//Copy
			this.s3Client.copyObject(copyObjRequest);

			System.out.println(String.format("Finish copying [%s] to [%s]", orgKey, copyKey));

		} catch (SdkClientException e) {
			System.out.println(String.format("copy to [%s] failed", copyKey));
		}
	}

	public void delete(String key) {
		try {
			//Delete 객체 생성
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, key);
			//Delete
			this.s3Client.deleteObject(deleteObjectRequest);
			System.out.println(String.format("[%s] deletion complete", key));

		} catch (SdkClientException e) {
			System.out.println(e.getMessage());
		}
	}

}
