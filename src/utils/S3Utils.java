package utils;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import connection.S3Client;

public class S3Utils {
	public static void createBucket() throws Exception {
		AmazonS3 s3client = S3Client.CreateS3Client();
		String bucket_name = S3Client.BUCKET_NAME;
		
		s3client.createBucket(bucket_name);
	}
	
	public static List<Bucket> listS3Buckets() throws Exception {
		AmazonS3 s3client = S3Client.CreateS3Client();
		List<Bucket> buckets = s3client.listBuckets();
		return buckets;
	}
	
	public static void uploadFile() throws Exception {
		String bucket_name = S3Client.BUCKET_NAME;
		
	}
}
