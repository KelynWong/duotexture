package connection;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Client {
	// global variables
	final static String BUCKET_NAME = "jad-bucket";
	
	public static AmazonS3 CreateS3Client() throws Exception{
		// pre-define variables
		final String ACCESS_KEY_ID = "AKIA3BFIE64ZPYIK2TGY";
		final String ACCESS_SEC_KEY = "MbSuvs0l1/o3J0RMNkR0eOxvElynukO6mIRNLnr+";
		
		// credentials object identifying user for authentication (user are given AmazonS3FullAccess)
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, ACCESS_SEC_KEY);
		
		// create a client connection based on credentials
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_2)
				.build();;
		
		return s3client;
	}
}
