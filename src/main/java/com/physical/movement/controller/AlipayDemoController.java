package com.physical.movement.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.physical.movement.config.AlipayConfig;
import com.physical.movement.entity.Court;
import com.physical.movement.entity.SysUser;
import com.physical.movement.entity.Team;
import com.physical.movement.service.CourtService;
import com.physical.movement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * 支付宝支付
 */
@Controller
@RequestMapping("payment")
public class AlipayDemoController {

    public static final String COST = "50";


    @Autowired
    CourtService courtService;

    @Autowired
    TeamService teamService;

    @RequestMapping(value = "goAlipay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(HttpServletRequest request, HttpSession session, HttpServletRequest response, Integer id) throws Exception {


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString();
        //付款金额，必填
        String total_amount = COST;

        if (id == null)
            id = 0;
        session.setAttribute(out_trade_no, id);
        //订单名称，必填
        String subject = "预付" + "50" + "元";
        //商品描述，可空
        String body = "预付";

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    @RequestMapping("returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException, AlipayApiException {
        response.setContentType("text/html;charset=utf-8");
        boolean verifyResult = rsaCheckV1(request);
        if (verifyResult) {
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号
            //商户订单号
            SysUser sysUser = (SysUser) session.getAttribute("sysUser");
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            Integer id = (Integer) session.getAttribute(out_trade_no);
            session.removeAttribute(out_trade_no);
            Court court = new Court();
            Team team = new Team();
            team.setTeamleaderid(sysUser.getId());
            team = teamService.select(team);
            court.setTeamname(team.getTeamname());
            court.setTid(team.getId());
            court.setId(id);
            courtService.updateByPrimaryKeySelective(court);
            return "redirect:/admin/index";
        } else {
            return "redirect:/error";
        }
    }

    @RequestMapping("toView")
    public String view(String viewName) {
        return viewName;
    }

    public boolean rsaCheckV1(HttpServletRequest request) {
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(params,
                    AlipayConfig.merchant_private_key,
                    AlipayConfig.charset,
                    AlipayConfig.sign_type);

            return verifyResult;
        } catch (AlipayApiException e) {

            return true;
        }
    }

    @RequestMapping("view")
    public String view() {
        return "home";
    }

    @RequestMapping("notify")
    public String notify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder("/****************************** alipay notify ******************************/\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n"));
//        log.info(notifyBuild.toString());


        boolean flag = this.rsaCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if (tradeStatus.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }
            System.out.println("回调验证数据");
            return "redirect:admin/index";
        }

        return "redirect:error";
    }
}