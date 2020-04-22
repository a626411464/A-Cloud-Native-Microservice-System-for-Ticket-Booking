package com.hao.springcloud.dao;

import com.hao.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao
{
    //1 新建订单 返回订单
    void create(Order order);
    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
    //3 查询所有订单
    List<Order> findAll();
    //4 根据ID查询一点
    Order orderfindByid(@Param("id") Long new_orderId);
    //5 修改订单状态，改为2
    void cannel(@Param("id") Long id);

    void refund(@Param("id") Long orderId);
}