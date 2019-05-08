package com.typ.travel.web.servlet;

import com.typ.travel.entity.PageBean;
import com.typ.travel.entity.Route;
import com.typ.travel.entity.User;
import com.typ.travel.service.FavoriteService;
import com.typ.travel.service.RouteService;
import com.typ.travel.service.impl.FavoriteServiceImpl;
import com.typ.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author typ
 * @date 2019/4/17 19:20
 * @Description: ${PACKAGE_NAME}
 */
@WebServlet("/route/*")
@SuppressWarnings("all")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受rname 线路名称
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");


        int cid = 0;
        //类别id
        //2.处理参数
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        //当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;
        //每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        //3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 根据id查询一条路线的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //获取id参数
        String rid = request.getParameter("rid");
        //调用service方法完成查询
        Route route=routeService.findOne(rid);
        //转为json数据返回客户端
        writeValue(route,response);

    }
    public void isFavorite(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        //获取rid参数
        String rid = request.getParameter("rid");
        //获取用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid=0;
        }else{
            uid=user.getUid();
        }
        //调用service方法查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        writeValue(flag,response);
    }
    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取线路rid
        String rid = request.getParameter("rid");
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            return;
        }else{
            //用户已经登录
            uid = user.getUid();
        }


        //3. 调用service添加
        favoriteService.add(rid,uid);

    }

}
