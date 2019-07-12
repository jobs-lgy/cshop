package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.form.ChangePasswordForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author june
 * @createTime 2019-06-27 10:20
 * @see
 * @since
 */
@Controller
@RequestMapping("/account/password")
public class ChangePasswordController {
    @RequestMapping(method = RequestMethod.POST)
    public CommonResponse processChangePassword(HttpServletRequest request, Model model,
                                                @ModelAttribute("changePasswordForm") ChangePasswordForm form, RedirectAttributes redirectAttributes) {
        return CommonResponse.success();
    }
}
