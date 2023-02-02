package com.te.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.te.mail.studententity.Student;

@SpringBootApplication
public class EmailbyattechmentApplication {

	@Autowired
	private JavaMailSender mailSender;

	public static void main(String[] args) {
		SpringApplication.run(EmailbyattechmentApplication.class, args);
	}

	public void mail(Student student) throws MessagingException, MailException {
		String attachment = "C:/Users/Rushikesh/Documents/workspace-spring-tool-suite-4-4.16.0.RELEASE/emailbyattechment/src/main/resources/logo1.png";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("patilhrushikesh525@gmail.com");
		helper.setTo(student.getStudentEmail());
		helper.setSubject(
				"Addmission Confirmed in  Jayanagar English Medium High School in " + student.getStudentStandard() + " Standard");
		helper.setText("<html><body><h1>" + "Welcome To Jayanagar English Medium High School " + student.getStudentFirstName() + " "
				+ student.getStudentLastName() + "</h1>"
				+ "<img src=https://www.edustoke.com/assets/uploads-new/bes-high-school-1492422090-2.jpg  alt=Building Image><br>"
				+ "<h2>Dear Student,<br><p>Jayanagar English Medium High School was established during the year 1986-87 by Bharath Education Society. The School has highly qualified, efficient, ethical and dedicated teaching staff. They put forth collective efforts to infuse courage and confidence in students in order to achieve astounding results it has secured.The institution continues to strive hard to achieve Excellency in education.BES offers most integrated education to students belonging to all strata of society. The main objective of BES is to facilitate education to students from rural and financially deprived section. The idea is to position them at a level with the best in the society in terms of educational qualification, personality and high value system.BES is located at a strategic position ie. in the vicinity of Jayanagar, 4th block bus stop. The location allows students to commute with ease from most far flung areas.We, at BES have taken all opportunities to make the Institute most student friendly in terms of facilities like laboratory, library hostel accommodation for girls, canteen, large playing area,auditorium /seminar halls etc.All these facilities are intended at offering a holistic development of the all round growth of the young minds.We also encourage all types of extra curricular activities, so that students can blossom with their hidden talents<p></h2>"
				+ "</html></body>", true);

		FileSystemResource resource = new FileSystemResource(new File(attachment));
		helper.addAttachment(resource.getFilename(), resource);
		mailSender.send(message);
	}
}
