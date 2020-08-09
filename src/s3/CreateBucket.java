package s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import connection.S3Client;

public class CreateBucket {
	public static void main(String[] args) {
		try {
			AmazonS3 s3client = S3Client.CreateS3Client();
			String bucket_name = "jad-bucket";
			
			s3client.createBucket(bucket_name);
		} catch (AmazonS3Exception e) {
			System.out.println("(CreateBucket.java) AmazonS3Exception Error: " + e + "\n");
		} catch (Exception e) {
			System.out.println("(CreateBucket.java) Error: " + e + "\n");
		}
	}
}
