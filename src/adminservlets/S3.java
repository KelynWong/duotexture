package adminservlets;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;

import utils.S3Utils;

public class S3 {
	public static void main(String args[]) {
		try {
			S3Utils.createBucket();
			S3Utils.createFolder();
			List<Bucket> buckets = S3Utils.listS3Buckets();
			for (Bucket b: buckets) {
				System.out.println("* " + b.getName());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
