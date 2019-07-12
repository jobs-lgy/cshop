/*
 * #%L
 * BroadleafCommerce Common Libraries
 * %%
 * Copyright (C) 2009 - 2016 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 *
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package com.javachen.email.service.message;

import com.javachen.email.domain.EmailInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

@Slf4j
public class NullMessageCreator extends MessageCreator {
    public NullMessageCreator(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    @Override
    public String buildMessageBody(EmailInfo info, Map<String, Object> props) {
        return info.getTemplate();
    }

    @Override
    public void sendMessage(final Map<String, Object> props) throws MailException {
        log.warn("NullMessageCreator is defined -- specify a real message creator to send emails");
    }

}
