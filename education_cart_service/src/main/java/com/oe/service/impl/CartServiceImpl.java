package com.oe.service.impl;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.pojo.Cart;

import com.oe.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @ClassName CartServiceImpl
 * @Author RC
 * @date 2021.02.02 14:55
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private TeacherFeign teacherFeign;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CartServiceImpl.class);


    @Override
    public List<Cart> addCart(List<Cart> cartList, Long courseId) {

        //cartList 已经存在额购物车,courseId要添加的课程
        //第一种可能性:购物车没有数据,或者购物车中有但是没有在当前店铺(商家)买东西
        //第二种可能性:购物车有数据,也有这个店铺的东西,但是没有这门课程
        //第三种可能性:购物车有数据,也有店铺的东西,也买过这门课程






//        //1.根据商品SKU ID查询SKU商品信息(itemId)
//        TbItem item = itemService.findItemByItemId(itemId);
        DataResult<TabCourseVO> courseById = teacherFeign.findCourseById(courseId);

//        //2.获取课程信息
        TabCourseVO courseVO = courseById.getData();
        String teacherName = courseVO.getTeacherName();
        // 根据课程获取教师信息
        DataResult<TabTeacherVO> teacherByName = teacherFeign.findTeacherByName(courseVO.getTeacherName());
        TabTeacherVO teacherVO = teacherByName.getData();

        String teacherId = teacherVO.getTeacherId();
        //3.根据商家ID判断购物车列表中(cartList)是否存在该商家的购物车
        Cart cart = getCartBySellerId(cartList, teacherId);
        //4.如果购物车列表中不存在该商家的购物车
        if(cart == null){
            //4.1 新建购物车对象
            cart = new Cart();
            //4.2 将新建的购物车对象添加到购物车列表
            cart.setTeacherId(teacherId);
            cart.setName(teacherName);
            List<TabCourseVO> courseList = new ArrayList<>();
            TabCourseVO course = new TabCourseVO();

            course.setTeacherName(teacherName);
            course.setAuditStatus(courseVO.getAuditStatus());
            course.setPrice(courseVO.getPrice());
            course.setImage(courseVO.getImage());
            course.setCaption(courseVO.getCaption());
            course.setCourseName(courseVO.getCourseName());
            course.setCourseIntroduction(courseVO.getCourseIntroduction());
            course.setAuditStatus(courseVO.getAuditStatus());
            course.setTitle(courseVO.getTitle());
            courseList.add(course);
            cart.setCourseVOList(courseList);
            //将新创建的购物车放到购物车列表中
            cartList.add(cart);
        }else {
            //5.如果购物车列表中存在该商家的购物车(也就是sellerId不为null)
            //6查询购物车明细列表中是否存在该商品
            //6.1. 如果没有，新增购物车明细(list添加)
//           if(orderItemList.size() <=0 ){
//                // 因此要将当前店铺的购物车从购物车列表中清除出去
//                cartList.remove(cart);
//            }
        }
        return cartList;
    }
    //根据课程ID判断购物车列表中(cartList)是否存在该商家的购物车
    private Cart getCartBySellerId(List<Cart> cartList, String teacherId) {
        for (Cart cart : cartList) {
            if (Objects.equals(cart.getTeacherId(), teacherId)) {
                //有返回 cart
                return cart;
            }
        }
        //没有return null
        return null;
    }

    //从redis缓存获取购物车数据
    @Override
    public List<Cart> findCartListFormRedis(String studentName) {

        List<Cart> cartList =(List<Cart>) redisTemplate.boundHashOps("cartList").get(studentName);
        //判断从redis获取数据是否为空
        if(cartList == null){
            //为空就new 一个新的对象防止空指针异常
            cartList = new ArrayList<>();
        }
        return cartList;
    }
    //向redis缓存添加购物车数据  carList 大KEY  userId 小KEY
    @Override
    public void saveCartListToRedis(String name, List<Cart> cartList) {

        redisTemplate.boundHashOps("cartList").put(name,cartList);
    }
    //合并购物车 cookie 和redis
    @Override
    public List<Cart> margeCarListCookieAndRedis(List<Cart> cartListCookie, List<Cart> cartListFormRedis) {
        for (Cart cartCookie : cartListCookie) {
            //拿出cartCookie中每一条orderItem和现在要添加的组装在一起
            for (TabCourseVO tabCourseVO : cartCookie.getCourseVOList()) {
                cartListFormRedis = addCart(cartListFormRedis, tabCourseVO.getId());
            }
        }
        return cartListFormRedis;//追加好的
    }
}
