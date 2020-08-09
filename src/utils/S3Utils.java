package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import connection.S3Client;

public class S3Utils {
	
	// create s3 bucket
	public static void createBucket() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// declare variable
		String bucket_name = S3Client.BUCKET_NAME;
		
		// create s3 bucket
		s3client.createBucket(bucket_name);
	}
	
	// delete s3 bucket
	public static void deleteBucket() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// declare variable
		String bucket_name = S3Client.BUCKET_NAME;
		
		// create s3 bucket
		s3client.deleteBucket(bucket_name);
	}
	
	// get list of all buckets in s3
	public static List<Bucket> listS3Buckets() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// get list of buckets
		List<Bucket> buckets = s3client.listBuckets();
		
		// return results
		return buckets;
	}
	
	// create folder in s3 bucket
	public static void createFolder() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// declare variable
		String bucket_name = S3Client.BUCKET_NAME;
		String folder_name = S3Client.FOLDER_NAME;
		String SUFFIX = S3Client.SUFFIX;
				
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		// create empty content+
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket_name, folder_name + SUFFIX, emptyContent,
				metadata);

		// send request to S3 to create folder
		s3client.putObject(putObjectRequest);
	}
	
	// delete folder in s3 bucket
	public static void deleteFolder() {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// declare variable
		String bucket_name = S3Client.BUCKET_NAME;
		String folder_name = S3Client.FOLDER_NAME;
				
		// get list of objects in folder
		List fileList = s3client.listObjects(bucket_name, folder_name).getObjectSummaries();
		
		// for each object in fileList
		for (Object object : fileList) {
			// get file from object
			S3ObjectSummary file = (S3ObjectSummary) object;
			
			// delete file
			s3client.deleteObject(bucket_name, file.getKey());
		}
		
		// delete folder
		s3client.deleteObject(bucket_name, folder_name);
	}
	
	// upload file into s3 bucket
	public static void uploadFile(String object_name, String file_path) throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
				
		// pre-define variables
        String bucket_name = S3Client.BUCKET_NAME;

        s3client.putObject(bucket_name, object_name, new File(file_path));
	}
	
	// get object from s3 bucket
	public void getObject(String objectName, String file_path)  {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
				
		// pre-define variables
        String bucketName = S3Client.BUCKET_NAME;

        try {
        S3Object s3object = s3client.getObject(bucketName, objectName);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
