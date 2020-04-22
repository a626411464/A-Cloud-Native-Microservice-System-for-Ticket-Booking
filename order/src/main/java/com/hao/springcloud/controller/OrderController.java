package com.hao.springcloud.controller;
import com.hao.springcloud.domain.CommonResult;
import com.hao.springcloud.domain.Order;
import com.hao.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@RestController
public class OrderController
{
    @Resource
    private OrderService orderService;
    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        Order new_order=orderService.create(order);
        return new CommonResult(200,"订单创建成功 ",new_order);
    }

    @GetMapping("/order/confirm")
    public CommonResult confirm(long orderId,int pay){
        int status=orderService.confirm(orderId);
        String s=null;
        Order order=orderService.orderfindByid(orderId);
        if(order!=null){
            if(pay==1&&status==0){
                s=orderService.continued(order);
            }else if(pay==0&&status==0){
                orderService.cannel(order);
                s="取消成功";
            }else{
                s="取消失败，请尝试申请退款";
            }
            order=orderService.orderfindByid(orderId);
            return new CommonResult(200,s,order);
        }else{
            return new CommonResult(404,"订单不存在");
        }
    }
    @GetMapping("/order/confirmtest")
    public CommonResult confirmtest(long orderId,int pay){
        Order order=orderService.orderfindByid(orderId);
        String s=orderService.breakOrder(order);
        order=orderService.orderfindByid(orderId);
        return new CommonResult(200,s,order);
        }

    @GetMapping("/order/refund")
    public CommonResult refund(long orderId) {

        Order order = orderService.orderfindByid(orderId);
        if (order != null && order.getStatus() == 1) {
            orderService.refund(orderId);
            return new CommonResult(200, "订单退款成功 ");
        } else {
            return new CommonResult(404, "订单不存在");
        }
    }
    @PostMapping("/order/findAll")
    public  CommonResult findAll(){
        return new CommonResult(200,"查询成功",orderService.findAll());
    }
}
