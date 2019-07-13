package com.javachen.cshop.email;

import com.javachen.cshop.email.domain.EmailOpen;
import com.javachen.cshop.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author june
 * @createTime 2019-06-27 12:56
 * @see
 * @since
 */
@Slf4j
public class EmailOpenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EmailService emailService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        Long emailId = null;

        // Parse the URL for the Email ID and Image URL
        if (url != null) {
            String[] items = url.split("/");
            emailId = Long.valueOf(items[1]);
        }

        // Record the open
        if (emailId != null) {
            log.debug("service() => Recording Open for Email[" + emailId + "]");
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            String userAgent = request.getHeader("USER-AGENT");
            Map<String, String> extraValues = new HashMap<String, String>();
            extraValues.put("userAgent", userAgent);

            EmailOpen emailOpen=new EmailOpen();
            emailOpen.setOpenTime(new Date());
            emailOpen.setEmailRecordId(emailId);
            emailOpen.setUserAgent(userAgent);
            emailService.emailOpened(emailOpen);
        }
        response.setContentType("image/gif");
        BufferedInputStream bis = null;
        OutputStream out = response.getOutputStream();
        try {
            bis = new BufferedInputStream(EmailOpenServlet.class.getResourceAsStream("clear_dot.gif"));
            boolean eof = false;
            while (!eof) {
                int temp = bis.read();
                if (temp == -1) {
                    eof = true;
                } else {
                    out.write(temp);
                }
            }
        } finally {
            if (bis != null) {
                try{ bis.close(); } catch (Throwable e) {
                    log.error("Unable to close output stream in EmailOpenServlet", e);
                }
            }
            //Don't close the output stream controlled by the container. The container will
            //handle this.
        }
    }
}
