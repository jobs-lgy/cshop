package com.javachen.cshop.email.web;

import com.javachen.cshop.email.entity.EmailClick;
import com.javachen.cshop.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author june
 * @createTime 2019-06-27 12:54
 * @see
 * @since
 */
public class EmailClickFilter implements Filter {
    @Autowired
    private EmailService emailService;

    @Override
    public void destroy() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String emailId = request.getParameter("emailId");
        if (emailId != null) {
            EmailClick emailClick = new EmailClick();
            emailClick.setEmailRecordId(Long.valueOf(emailId));
            emailClick.setClickedTime(new Date());
            emailClick.setUrl(((HttpServletRequest) request).getRequestURI());
            emailService.emailClicked(emailClick);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
