package org.sharing.proto.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

public class AWSConnector {
	private static final String AWS_ACCESS_KEY = System.getProperty("access_key");
	private static final String AWS_SECRET_KEY = System.getProperty("secret_key");
	private static AmazonS3Client s3;

	private AWSConnector() {
	}	
	
	public static AmazonS3Client getS3() {
		if(s3 == null) s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
		return s3;
	}	
}
