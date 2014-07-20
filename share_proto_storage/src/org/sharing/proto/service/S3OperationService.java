package org.sharing.proto.service;

import java.io.File;
import java.util.List;

import com.amazonaws.services.s3.model.S3Object;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class S3OperationService {

	public PutObjectInfo putObject(final String bucketName, final String objectPath) {
		Preconditions.checkNotNull(bucketName);
		Preconditions.checkNotNull(objectPath);

		File file = new File(objectPath);
		Preconditions.checkArgument(file.exists(), "file not exists");

		String key = bucketName + "/" + objectPath;
		S3ObjectHandler worker = new S3ObjectHandler(AWSConnector.getS3());
		worker.putObject(bucketName, key, file);
		return new PutObjectInfo(bucketName, Lists.newArrayList(key));
	}

	public PutObjectInfo mapToBucket(final String bucketName, final String directoryPath) {
		Preconditions.checkNotNull(directoryPath);
		File directory = new File(directoryPath);
		Preconditions.checkArgument(directory.isDirectory(), "file must be a directory");

		List<String> objectNames = Lists.newArrayList();
		File[] objects = directory.listFiles();

		if (objects != null && objects.length > 0) {
			S3ObjectHandler worker = new S3ObjectHandler(AWSConnector.getS3());
			worker.createBucket(bucketName);
			Lists.newArrayList(objects).forEach(f -> {
				String key = new StringBuilder(bucketName).append("/").append(f.getName()).toString();
				worker.putObject(bucketName, key, f);
				objectNames.add(key);
			});
		}

		return new PutObjectInfo(bucketName, objectNames);
	}

	public S3Object getObject(final String bucketName, final String key) {
		Preconditions.checkNotNull(bucketName);
		Preconditions.checkNotNull(key);

		return new S3ObjectHandler(AWSConnector.getS3()).getObject(bucketName, key);
	}

	public void deleteObject(String bucket, final String object) {
		S3ObjectHandler worker = new S3ObjectHandler(AWSConnector.getS3());
		worker.deleteObject(bucket, bucket + "/" + object);
	}

	public void deleteBucket(final String bucket) {
		S3ObjectHandler worker = new S3ObjectHandler(AWSConnector.getS3());
		worker.deleteBucket(bucket);
	}

	public static class PutObjectInfo {
		private String bucketName;
		private List<String> objectNames;

		public PutObjectInfo(String bucketName, List<String> objectNames) {
			this.bucketName = bucketName;
			this.objectNames = objectNames;
		}

		public String getBucketName() {
			return bucketName;
		}

		public List<String> getObjectNames() {
			return objectNames;
		}

		@Override
		public String toString() {
			return "bucket : " + bucketName + " -> objects " + objectNames + "";
		}

	}
}
