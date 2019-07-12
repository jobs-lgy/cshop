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
package com.javachen.email.service.jms;

import com.javachen.email.service.message.EmailServiceProducer;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

public interface JMSEmailServiceProducer extends EmailServiceProducer {


    public JmsTemplate getEmailServiceTemplate();

    public void setEmailServiceTemplate(JmsTemplate emailServiceTemplate);


    public Destination getEmailServiceDestination();

    public void setEmailServiceDestination(Destination emailServiceDestination);

}
