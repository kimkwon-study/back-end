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
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSuccessService {

    private final EmailSuccessRepository repository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void create(String email) {
        Optional<EmailSuccess> enable_email = repository.findByEmail(email);
        if(enable_email.isEmpty()){
            String successKey = randomSuccess();
            send_email(email, successKey);
            repository.save(EmailSuccess.getEntity(email,successKey));
        }else{
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


    @Async
    protected void send_email(String email, String random) {

        MimeMessage m = mailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
        try {
            h.setFrom(from);
            h.setTo(email);
            h.setSubject("망고플레이트 인증번호 발송");
            h.setText("인증번호 : " + random);
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.EMAIL_NO_SUCCESS);
        }
        mailSender.send(m);
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
