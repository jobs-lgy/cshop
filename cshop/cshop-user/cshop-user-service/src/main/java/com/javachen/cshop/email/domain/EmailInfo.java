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
package com.javachen.cshop.email.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmailInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Integer REGISTER_TYPE = 1;
    private Integer type;
    private String template;
    private String subject;
    private String sourceAddress;
    private String messageBody;
    private String encoding = "UTF8";
    private List<Attachment> attachments = new ArrayList<Attachment>();

    private String sendEmailReliableAsync;
    private String sendAsyncPriority;

    private Long userId;

    public synchronized EmailInfo clone() {
        EmailInfo info = new EmailInfo();
        info.setAttachments(attachments);
        info.setTemplate(template);
        info.setType(type);
        info.setSourceAddress(sourceAddress);
        info.setMessageBody(messageBody);
        info.setSendAsyncPriority(sendAsyncPriority);
        info.setSendEmailReliableAsync(sendEmailReliableAsync);
        info.setSubject(subject);
        info.setEncoding(encoding);
        info.setUserId(userId);
        return info;
    }
}
