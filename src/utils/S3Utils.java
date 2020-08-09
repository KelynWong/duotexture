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
	public static void createBucket() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// declare variable
		String bucket_name = S3Client.BUCKET_NAME;
		
		// create s3 bucket
		s3client.createBucket(bucket_name);
	}
	
	public static void deleteBucket() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// declare variable
		String bucket_name = S3Client.BUCKET_NAME;
		
		// create s3 bucket
		s3client.deleteBucket(bucket_name);
	}
	
	public static void createFolder(String bucketName, String folderName, AmazonS3 client, String SUFFIX) {
		
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		// create empty content+
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent,
				metadata);

		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}
	
	public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) {
		// get list of objects in folder
		List fileList = client.listObjects(bucketName, folderName).getObjectSummaries();
		
		// for each object in fileList
		for (Object object : fileList) {
			// get file from object
			S3ObjectSummary file = (S3ObjectSummary) object;
			
			// delete file
			client.deleteObject(bucketName, file.getKey());
		}
		
		// delete folder
		client.deleteObject(bucketName, folderName);
	}
	
	public static List<Bucket> listS3Buckets() throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
		
		// get list of buckets
		List<Bucket> buckets = s3client.listBuckets();
		
		// return results
		return buckets;
	}
	
	public static void uploadFile() throws Exception {
		String bucket_name = S3Client.BUCKET_NAME;
		
	}
	
	public void getObject(AmazonS3 s3client)  {
        String bucketName = S3Client.BUCKET_NAME;
        String objectName = S3Client.BUCKET_FILE_PATH;
        String downloadPath = S3Client.LOCAL_DOWNLOAD_PATH;

        try {
        S3Object s3object = s3client.getObject(bucketName, objectName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File(downloadPath));
        
        
        System.out.println("file copied to destination.");
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
