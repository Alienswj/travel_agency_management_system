package cn.edu.cuit.travel_agency_management_system.controller.backstage;


import cn.edu.cuit.travel_agency_management_system.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("/get_sale_group_by_month")
    public List<Integer> getSaleGroupByMonth(){

        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar);
        System.out.println(calendar.YEAR);
        System.out.println(calendar.MONTH);

        return null;
    }
}
