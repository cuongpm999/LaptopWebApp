package vn.ptit.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	@Bean
	public Cloudinary getCloudinary() {
	
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
		  "cloud_name", "cuongpham",
		  "api_key", "563118948926745",
		  "api_secret", "BXj88MsN5XQSBj8Y-CA5qUTVqr8"));
		
		return cloudinary;
	}

}
