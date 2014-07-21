package org.sharing.proto.client;

import org.sharing.proto.service.S3OperationService;
import org.sharing.proto.service.S3OperationService.PutObjectInfo;

public class DemoClient {
	/**
	 * This application needs aws-java-sdk jars and all the dependencies listed
	 * in its third-party dir and google guava jar in the classpath </br>
	 * 
	 * How to run ? </br> 1. Provide access_key and secret_key VM arguments
	 * </br> 2. pass the path of the root dir where files are located </br> 3.
	 * create a bucket and set that name, provide some file items to be uploaded
	 * </br> 3. run main method </br>
	 */
	public static void main(String[] args) {
		String resourceRoot = "";
		String bucketName = "shareknowledgeprototype";
		S3OperationService service = new S3OperationService();

		System.out.println("uploading an image to S3....");
		PutObjectInfo response = service.putObject(bucketName, resourceRoot + "_D714459.jpg");
		System.out.println("image uploaded " + response);

		System.out.println("uloading more items.......");
		response = service.mapToBucket(bucketName, resourceRoot + "items");
		System.out.println("uploaded items " + response);

	}
}
