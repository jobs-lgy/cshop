package com.javachen.cshop.email.message;

import com.javachen.cshop.email.domain.Attachment;
import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.domain.EmailTarget;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.Map;

public abstract class MessageCreator {

    private JavaMailSender javaMailSender;

    public MessageCreator(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(EmailInfo emailInfo, EmailTarget emailTarget,final Map<String, Object> props) throws MailException {
        MimeMessagePreparator preparator = buildMimeMessagePreparator(emailInfo,emailTarget,props);
        this.javaMailSender.send(preparator);
    }

    public abstract String buildMessageBody(String template, Map<String, Object> props);

    public MimeMessagePreparator buildMimeMessagePreparator(EmailInfo emailInfo, EmailTarget emailTarget,final Map<String, Object> props) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                boolean isMultipart = !CollectionUtils.isEmpty(emailInfo.getAttachments());
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, emailInfo.getEncoding());
                message.setTo(emailTarget.getToAddress());
                message.setFrom(emailInfo.getFromAddress());
                message.setSubject(emailInfo.getSubject());
                if (emailTarget.getBccAddresses() != null && emailTarget.getBccAddresses().length > 0) {
                    message.setBcc(emailTarget.getBccAddresses());
                }
                if (emailTarget.getCcAddresses() != null && emailTarget.getCcAddresses().length > 0) {
                    message.setCc(emailTarget.getCcAddresses());
                }
                String messageBody = emailInfo.getMessageBody();
                if (messageBody == null) {
                    messageBody = buildMessageBody(emailInfo.getTemplate(), props);
                }
                message.setText(messageBody, true);
                for (Attachment attachment : emailInfo.getAttachments()) {
                    ByteArrayDataSource dataSource = new ByteArrayDataSource(attachment.getData(), attachment.getMimeType());
                    message.addAttachment(attachment.getFilename(), dataSource);
                }
            }
        };
        return preparator;

    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
