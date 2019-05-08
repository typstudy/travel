package com.typ.travel.service.impl;

import com.typ.travel.dao.FavoriteDao;
import com.typ.travel.dao.RouteDao;
import com.typ.travel.dao.RouteImgDao;
import com.typ.travel.dao.SellerDao;
import com.typ.travel.dao.impl.FavoriteDaoImpl;
import com.typ.travel.dao.impl.RouteDaoImpl;
import com.typ.travel.dao.impl.RouteImgDaoImpl;
import com.typ.travel.dao.impl.SellerDaoImpl;
import com.typ.travel.entity.PageBean;
import com.typ.travel.entity.Route;
import com.typ.travel.entity.RouteImg;
import com.typ.travel.entity.Seller;
import com.typ.travel.service.RouteService;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 19:37
 * @Description: com.typ.travel.service.impl
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao=new RouteDaoImpl();

    private RouteImgDao routeImgDao=new RouteImgDaoImpl();

    private SellerDao sellerDao=new SellerDaoImpl();

    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,
                                     String rname) {
        //封装PageBean
        PageBean<Route> pb=new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        //开始的记录数
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;

    }

    @Override
    public Route findOne(String rid) {
        //通过rid查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //通过route的rid 查询图片集合
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //将集合设置到route对象
        route.setRouteImgList(routeImgList);
        //根据route的sid查询出商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //查询收藏次数
        int count=favoriteDao.findByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
