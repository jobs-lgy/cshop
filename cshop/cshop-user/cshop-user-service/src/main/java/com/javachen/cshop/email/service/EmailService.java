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
package com.javachen.cshop.email.service;

import com.javachen.cshop.email.domain.EmailClick;
import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.domain.EmailOpen;
import com.javachen.cshop.email.domain.EmailTarget;

import java.util.Map;

public interface EmailService {

    public boolean sendTemplateEmail(String email, EmailInfo emailInfo, Map<String, Object> props);

    public boolean sendTemplateEmail(EmailTarget emailTarget, EmailInfo emailInfo, Map<String, Object> props);

    public boolean sendBasicEmail(EmailInfo emailInfo, EmailTarget emailTarget, Map<String, Object> props);

    void emailOpened(EmailOpen emailOpen);

    void emailClicked(EmailClick emailClick);
}
