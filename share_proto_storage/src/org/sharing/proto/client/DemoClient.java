package org.sharing.proto.client;

import org.sharing.proto.service.S3OperationService;
import org.sharing.proto.service.S3OperationService.PutObjectInfo;

public class DemoClient {
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
