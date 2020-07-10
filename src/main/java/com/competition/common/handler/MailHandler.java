package com.competition.common.handler;

import org.springframework.stereotype.Component;

@Component
public class MailHandler {

//		private JavaMailSender sender;
//		private MimeMessage message;
//		private MimeMessageHelper messageHelper;
//		
//		public MailHandler(JavaMailSender sender) throws MessagingException {
//			this.sender = sender;
//			message = sender.createMimeMessage();
//			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//		}
//		
//		public void setFrom(String add) throws MessagingException {
//			messageHelper.setFrom(add);
//		}
//		
//		public void setTo(String email) throws MessagingException {
//			messageHelper.setTo(email);
//		}
//		
//		public void setSubject(String sub) throws MessagingException {
//			messageHelper.setSubject(sub);
//		}
//		public void setText(String text, boolean html) throws MessagingException {
//			messageHelper.setText(text, html);
//		}
//		public void setAttach(String display, String attach) throws MessagingException, IOException {
//			File file = new ClassPathResource(attach).getFile();
//			FileSystemResource sr = new FileSystemResource(file);
//			
//			messageHelper.addAttachment(display, sr);
//		}
//		public void setInline(String id, String inline) throws MessagingException, IOException {
//			File file = new ClassPathResource(inline).getFile();
//			FileSystemResource sr = new FileSystemResource(file);
//			
//			messageHelper.addInline(id, sr);
//		}
//		
//		public void send() {
//			try {
//				sender.send(message);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
}
