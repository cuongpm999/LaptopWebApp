package vn.ptit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Day la file cau hinh Spring MVC cho project
 * 
 * @author admin
 */

@Configuration /* bao hieu cho spring boot biet day la file cau hinh */
@EnableWebMvc /* file cau hinh nay dung cho Web MVC */
@ComponentScan(basePackages = { "vn.ptit" }) /* chi ro cho spring mvc biet noi chua cac controller */
public class WebMVC implements WebMvcConfigurer {

	/**
	 * chỉ cho spring-mvc biết nơi chứa các view đã tạo ở phần 1: front-end.
	 * 
	 * @return
	 */
	
	@Value("${file.upload.path}")
	private String attachmentPath;
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/META-INF/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/META-INF/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/META-INF/img/");
		registry.addResourceHandler("/favicons/**").addResourceLocations("classpath:/META-INF/favicons/");
		registry.addResourceHandler("/files_laptops/**").addResourceLocations(attachmentPath);
		registry.addResourceHandler("/files_banners/**").addResourceLocations(attachmentPath);
		registry.addResourceHandler("/files_users/**").addResourceLocations(attachmentPath);
		
	}

}
