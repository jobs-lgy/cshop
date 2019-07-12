package com.javachen.email.service.message;

import com.javachen.email.domain.Attachment;
import com.javachen.email.domain.EmailInfo;
import com.javachen.email.domain.EmailTarget;
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

    public void sendMessage(final Map<String, Object> props) throws MailException {
        MimeMessagePreparator preparator = buildMimeMessagePreparator(props);
        this.javaMailSender.send(preparator);
    }

    public abstract String buildMessageBody(EmailInfo info, Map<String, Object> props);

    public MimeMessagePreparator buildMimeMessagePreparator(final Map<String, Object> props) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                EmailTarget emailUser = (EmailTarget) props.get("target");
                EmailInfo info = (EmailInfo) props.get("info");
                boolean isMultipart = !CollectionUtils.isEmpty(info.getAttachments());
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, info.getEncoding());
                message.setTo(emailUser.getTargetAddress());
                message.setFrom(info.getSourceAddress());
                message.setSubject(info.getSubject());
                if (emailUser.getBccAddresses() != null && emailUser.getBccAddresses().length > 0) {
                    message.setBcc(emailUser.getBccAddresses());
                }
                if (emailUser.getCcAddresses() != null && emailUser.getCcAddresses().length > 0) {
                    message.setCc(emailUser.getCcAddresses());
                }
                String messageBody = info.getMessageBody();
                if (messageBody == null) {
                    messageBody = buildMessageBody(info, props);
                }
                message.setText(messageBody, true);
                for (Attachment attachment : info.getAttachments()) {
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
