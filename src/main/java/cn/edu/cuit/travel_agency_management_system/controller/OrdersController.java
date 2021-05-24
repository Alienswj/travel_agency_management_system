package cn.edu.cuit.travel_agency_management_system.controller;


import cn.edu.cuit.travel_agency_management_system.entity.*;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.BookingTripDTO;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.ChangeOrderDTO;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.OrdersWithTripDTO;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.UserWithRole;
import cn.edu.cuit.travel_agency_management_system.mapper.OrdersMapper;
import cn.edu.cuit.travel_agency_management_system.mapper.UserMapper;
import cn.edu.cuit.travel_agency_management_system.service.InventoryService;
import cn.edu.cuit.travel_agency_management_system.service.OrdersService;
import cn.edu.cuit.travel_agency_management_system.service.TripService;
import cn.edu.cuit.travel_agency_management_system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.cert.CertPathValidatorException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 时伟建
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    UserService userService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    TripService tripService;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    InventoryService inventoryService;
    @Transactional
    @PostMapping("/booking")
    public RespBean creatOrder(@RequestBody BookingTripDTO bookingTripDTO){
        System.out.println(bookingTripDTO.getIdNumber());
        if(ordersService.getOne(new QueryWrapper<Orders>()
                .eq("uid",bookingTripDTO.getUid())
                .eq("tid",bookingTripDTO.getTid())
                .eq("date",bookingTripDTO.getDate()))!=null){
            return RespBean.error("重复预订啦！");
        }
        userService.update().eq("id",bookingTripDTO.getUid())
                .set("name",bookingTripDTO.getName())
                .set("id_number",bookingTripDTO.getIdNumber())
                .set("phone", bookingTripDTO.getPhone())
                .update();
        Orders order=new Orders();
        order.setUid(bookingTripDTO.getUid());
        order.setTid(bookingTripDTO.getTid());
        order.setDate(bookingTripDTO.getDate());
        order.setQuantity(1);
        if(ordersService.save(order)){
            QueryWrapper<Inventory> inventoryQueryWrapper=new QueryWrapper<>();
            inventoryQueryWrapper.eq("tid",bookingTripDTO.getTid()).eq("date",bookingTripDTO.getDate());
            int newQuantity=inventoryService.getOne(inventoryQueryWrapper).getQuantity()-1;
            if(newQuantity>=0) {
                inventoryService.update()
                        .eq("tid", bookingTripDTO.getTid())
                        .eq("date", bookingTripDTO.getDate())
                        .set("quantity", newQuantity)
                        .update();
                return RespBean.ok("你已成功预订！");
            }
        }
        return RespBean.error("遇到未知错误！");
    }

    @PostMapping("/get_my_orders")
    public RespBean getMyOrders(@RequestBody String id){
        id=id.replace("=","");
        List<OrdersWithTripDTO> orders= ordersMapper.getMyOrders(id);
        orders.forEach(System.out::println);
        if(orders!=null){
            return RespBean.ok("查询成功",orders);
        }else{
            return null;
        }
    }



    @Transactional
    @PostMapping("/delete")
    public RespBean deleteOrder(@RequestBody String oid){
        oid=oid.replace("=","");
        if(ordersService.removeById(oid)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败，请重试");
        }
    }

    @Transactional
    @PostMapping("/refund")
    public RespBean refundOrder(@RequestBody OrdersWithTripDTO orders){
        ordersService.removeById(orders.getOid());
        int quantity=inventoryService.query().eq("tid",orders.getTid()).eq("date",orders.getDate()).one().getQuantity();
        System.out.println(quantity);
        inventoryService.update().eq("tid",orders.getTid()).eq("date",orders.getDate()).set("quantity",quantity+orders.getQuantity()).update();
        return RespBean.ok("退票成功！");
    }

    @Transactional
    @PostMapping("/change")
    public RespBean changeOrder(@RequestBody ChangeOrderDTO orders){
        int oquantity=inventoryService.query().eq("tid",orders.getTid()).eq("date",orders.getOdate()).one().getQuantity();
        inventoryService.update().eq("tid",orders.getTid()).eq("date",orders.getOdate()).set("quantity",oquantity+1).update();
        int nquantity=inventoryService.query().eq("tid",orders.getTid()).eq("date",orders.getNdate()).one().getQuantity();
        if(nquantity<=0){
            return RespBean.error("目标日期已售完！");
        }
        inventoryService.update().eq("tid",orders.getTid()).eq("date",orders.getNdate()).set("quantity",nquantity-1).update();
        ordersService.update().eq("id",orders.getOid()).set("date",orders.getNdate()).update();

        return RespBean.ok("改签成功！");

    }
}

