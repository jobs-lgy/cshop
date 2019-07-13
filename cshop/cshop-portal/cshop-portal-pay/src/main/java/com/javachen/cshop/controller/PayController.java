package com.javachen.cshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PayController {


    @RequestMapping("/pay")
    public String pay(String payToken, Model model) {
//        // 1.验证payToken参数
//        if (StringUtils.isEmpty(payToken)) {
//            setErrorMsg(model, "支付令牌不能为空!");
//            return ERROR_500_FTL;
//        }
//        // 2.使用payToken查询支付信息
//        BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoFeign.tokenByPayMentTransac(payToken);
//        if (!isSuccess(tokenByPayMentTransac)) {
//            setErrorMsg(model, tokenByPayMentTransac.getMsg());
//            return ERROR_500_FTL;
//        }
//        // 3.查询支付信息
//        PayMentTransacDTO data = tokenByPayMentTransac.getData();
//        model.addAttribute("data", data);
//        // 4.查询渠道信息
//        List<PaymentChannelDTO> paymentChanneList = paymentChannelFeign.selectAll();
//        model.addAttribute("paymentChanneList", paymentChanneList);
//        model.addAttribute("payToken", payToken);
        return "index";
    }

    /**
     * @param payToken
     * @return
     * @throws IOException
     */
    @RequestMapping("/payHtml")
    public void payHtml(String channelId, String payToken, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=utf-8");
//        BaseResponse<JSONObject> payHtmlData = payContextFeign.toPayHtml(channelId, payToken);
//        if (isSuccess(payHtmlData)) {
//            JSONObject data = payHtmlData.getData();
//            String payHtml = data.getString("payHtml");
//            response.getWriter().print(payHtml);
//        }

    }

}
