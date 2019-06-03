package com.priceline.hackathon.Email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.priceline.hackathon.Email.dto.User;

public class EmailSend {
	/*@Autowired
	private VelocityEngineFactoryBean velocityEngineFactoryBean;
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	 private void sendEmail(final User user) {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(user.getEmailAddress());
	            message.setFrom("webmaster@csonth.gov.uk"); // could be parameterized...
	            Map model = new HashMap();
	            model.put("user", user);
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	            		velocityEngineFactoryBean.createVelocityEngine(), "x.vm", model);
	            message.setText(text, true);
	         }
	      };
	      this.javaMailSenderImpl.send(preparator);
	   }
	*/
}
