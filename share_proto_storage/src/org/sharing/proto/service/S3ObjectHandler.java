package org.sharing.proto.service;

import java.io.File;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3ObjectHandler {
	private AmazonS3Client s3Client;

	public S3ObjectHandler(final AmazonS3Client clientP) {
		this.s3Client = clientP;
	}

	public Bucket createBucket(final String bucketName) {
		return s3Client.createBucket(bucketName);
	}

	public void deleteBucket(final String bucketName) {
		s3Client.deleteBucket(bucketName);
	}

	public S3Object getObject(final String bucketName, final String key) {
		return s3Client.getObject(bucketName, key);
	}

	public PutObjectResult putObject(final String bucketName, final String key, final File object) {
		return s3Client.putObject(bucketName, key, object);
	}

	public void deleteObject(final String bucketName, final String key) {
		s3Client.deleteObject(bucketName, key);
	}

	public void updateObject(final String bucketName, final String key, final File newObject) {
		s3Client.deleteObject(bucketName, key);
		putObject(bucketName, key, newObject);
	}

	public List<S3ObjectSummary> listObjects(String bucketName) {
		return s3Client.listObjects(bucketName).getObjectSummaries();
	}
}
