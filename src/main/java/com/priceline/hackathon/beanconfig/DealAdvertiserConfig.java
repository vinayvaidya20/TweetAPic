package com.priceline.hackathon.beanconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration("dealAdvertiserConfig")
public class DealAdvertiserConfig {
	
	@Bean
	public JavaMailSenderImpl getJavaMailSenderImpl() {
		return new JavaMailSenderImpl();
	}
	
	@Bean(name="velocityEngine")
	public VelocityEngineFactoryBean getVelocityEngineFactoryBean() {
		return new VelocityEngineFactoryBean();
	}
}
