package com.typ.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typ.travel.entity.ResultInfo;
import com.typ.travel.entity.User;
import com.typ.travel.service.UserService;
import com.typ.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author typ
 * @date 2019/4/17 14:26
 * @Description: ${PACKAGE_NAME}
 */
@WebServlet("/user/*")
@SuppressWarnings("all")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    /**
     * 注册方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        //验证验证码
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //5.将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //6.将json数据写回客户端
            //设置json编码
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装数据
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if (flag) {
            //注册成功
            info.setFlag(true);
        } else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
        }
        //5.将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //6.将json数据写回客户端
        //设置json编码
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    /**
     * 登录方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //获取验证码
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //5.将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //6.将json数据写回客户端
            //设置json编码
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //获取参数
        Map<String, String[]> map = request.getParameterMap();
        //封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service方法
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        //判断 用户是否为空
        if (u == null) {
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        } else if (u != null && !"Y".equals(u.getStatus())) {
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("用户尚未激活,请到邮箱激活");
        } else {
            //登录成功
            request.getSession().setAttribute("user", u);
            info.setFlag(true);
        }
        //响应数据
        writeValue(user,response);

    }

    /**
     * 激活方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        //判断
        if (code != null) {
            //调用service方法完成激活
            boolean flag = service.active(code);
            //判断标记
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='../login.html'>登录</a>";
            } else {
                //激活失败
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    /**
     * 退出方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        //跳转到登录ym
        response.sendRedirect(request.getContextPath() + "/login.html");

    }

    /**
     * 查询个人
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        //获取session中登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);

    }
}
