package utils;

import com.amazonaws.services.s3.AmazonS3;

import connection.S3Client;

public class S3Utils {
	public static void CreateBucket() throws Exception {
		AmazonS3 s3client = S3Client.CreateS3Client();
		String bucket_name = "jad-bucket";
		
		s3client.createBucket(bucket_name);
	}
}
