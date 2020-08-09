package utils;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import connection.S3Client;

public class S3Utils {
	public static void CreateBucket() throws Exception {
		AmazonS3 s3client = S3Client.CreateS3Client();
		String bucket_name = "jad-bucket";
		
		s3client.createBucket(bucket_name);
	}
	
	public static List<Bucket> ListS3Buckets() throws Exception {
		AmazonS3 s3client = S3Client.CreateS3Client();
		List<Bucket> buckets = s3client.listBuckets();
		return buckets;
	}
}
