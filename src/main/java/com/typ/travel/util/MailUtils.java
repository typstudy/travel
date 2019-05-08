package com.typ.travel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
@SuppressWarnings("all")
public final class MailUtils {
    // 发件人称号，同邮箱地址
    private static final String USER = "";
    // 如果是qq邮箱可以使户端授权码，或者登录密码
    private static final String PASSWORD = "";

    /**
     * @param to    收件人邮箱
     * @param text  邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */

    public static boolean sendMail(String to, String text, String title) {
        try {
            final Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.163.com");
            // 发件人的账号
            props.put("typstudy@163.com", USER);
            //发件人的密码
            props.put("typabcd123456", PASSWORD);
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props);
            //mailSession.setDebug(true);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            message.setFrom(new InternetAddress("typstudy@163.com"));
            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport transport=mailSession.getTransport();
            transport.connect("typstudy@163.com","typabcd123456");
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception { // 做测试用
        MailUtils.sendMail("typstudy@163.com", "你好，这是一封测试邮件，无需回复。", "测试邮件");
        System.out.println("hhhh");
    }


}
