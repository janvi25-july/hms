package com.property.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config {
	@Value("${cloud.aws.region}")
	private String region;
	@Value("${cloud.aws.acceskey}")
	private String accesKey;
	@Value("${cloud.aws.secretkey}")
	private String secretKey;
	
	@Bean
	public AmazonS3 amazons3() {
	BasicAWSCredentials credentials=new BasicAWSCredentials(accesKey,secretKey);
	return AmazonS3ClientBuilder.standard().withRegion(region)
			                               .withCredentials(new AWSStaticCredentialsProvider(credentials))
			                                .build();
	}

}
