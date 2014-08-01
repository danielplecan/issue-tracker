/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@EnableAsync
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfiguration;

    public Configuration getFreemarkerConfiguration() {
        return freemarkerConfiguration;
    }

    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    public MailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(final String email, final String subject, final Map<String, Object> map) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {
                try {
                    BufferedWriter writer = null;
                    
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                            true);
                    message.setTo(email);
                    message.setFrom("Graduates@endava.com");
                    
                    String emailText = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("template.html","UTF-8"), map);
                    message.setText(emailText, true);
                    message.setSubject(subject);
                    Date timestamp = new Date();
                    message.setSentDate(timestamp);
                } catch (TemplateException ex) {
                    Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (MailSendException e) {
            e.getStackTrace();
        }
    }
}
