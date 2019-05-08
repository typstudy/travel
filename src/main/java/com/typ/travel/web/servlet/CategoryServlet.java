package com.typ.travel.web.servlet;

import com.typ.travel.entity.Category;
import com.typ.travel.service.CategoryService;
import com.typ.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 14:47
 * @Description: ${PACKAGE_NAME}
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service=new CategoryServiceImpl();

    /**
     * 查询所有类别
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        //调用service方法
        List<Category> category = service.findAll();
        //将其序列化为json
        writeValue(category,response);

    }
}
