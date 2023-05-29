package com.mangoplate.mangoplate.service;

import com.mangoplate.mangoplate.domain.entity.EmailSuccess;
import com.mangoplate.mangoplate.domain.request.EmailRequest;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.EmailSuccessRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSuccessService {

    private final EmailSuccessRepository repository;



    public void create(String email) {
        Optional<EmailSuccess> enable_email = repository.findByEmail(email);
        if (enable_email.isEmpty()) {
            String successKey = randomSuccess();
            send_email(email, successKey);
            repository.save(EmailSuccess.getEntity(email, successKey));
        } else {
            String successKey = randomSuccess();
            send_email(email, successKey);
            enable_email.get().setSuccessKey(successKey);
            repository.save(enable_email.get());
        }
    }

    public void check(EmailRequest request) {
        EmailSuccess email = repository.findByEmail(request.email()).get();
        if (!(request.successKey().equals(email.getSuccessKey()))) {
            throw new ApplicationException(ErrorCode.EMAIL_NO_SUCCESS);
        }
    }


    protected void send_email(String email, String random) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername("ty_ty123@naver.com");
        javaMailSender.setPassword("");

        javaMailSender.setPort(465);

        javaMailSender.setJavaMailProperties(getMailProperties());

        //

        MimeMessage m = javaMailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
        try {
            h.setFrom("ty_ty123@naver.com");
            h.setTo(email);
            h.setSubject("망고플레이트 인증번호 발송");
            h.setText("인증번호 : " + random);
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.EMAIL_NO_SUCCESS);
        }
        javaMailSender.send(m);
    }


    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        return properties;
    }


    private String randomSuccess() {
        String tot = "";
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int buf = random.nextInt(9);
            tot += buf;
        }
        return tot;
    }


}
