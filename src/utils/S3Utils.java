package utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import connection.S3Client;

/**
 * 
 * Class: DIT/FT/2B/21
 * Group: 1
 * 
 * Name: LEE ZONG XUN RENFRED
 * Admin Number: P1935392 
 * 
 * Name: WONG EN TING KELYN
 * Admin Number: P1935800
 * 
 */

public class S3Utils {
	
	// upload file into s3 bucket
	public static String uploadFile(String object_name, InputStream file) throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
				
		// pre-define variables
        String bucket_name = S3Client.BUCKET_NAME;
        
        // make object_name unique
        String final_object_name = object_name + ( new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));

        // upload file
        s3client.putObject(new PutObjectRequest(bucket_name, final_object_name, file, new ObjectMetadata()));
        
        String imageUrl = String.valueOf(s3client.getUrl(
        		bucket_name, // The S3 Bucket To Upload To
                final_object_name)); //The key for the uploaded object
        
        return imageUrl;
	}
	
	// delete file in s3 bucket
	public static void deleteFile(String object_name) throws Exception {
		// create s3 client
		AmazonS3 s3client = S3Client.CreateS3Client();
				
		// pre-define variables
        String bucket_name = S3Client.BUCKET_NAME;
        
        // delete object
        s3client.deleteObject(bucket_name, object_name);
	}
}
