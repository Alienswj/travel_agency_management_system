package cn.edu.cuit.travel_agency_management_system.mapper;


import cn.edu.cuit.travel_agency_management_system.controller.backstage.StatisticsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestStatistics {
    @Autowired
    StatisticsController controller;

    @Test
    public void TestDate(){
        controller.getSaleGroupByMonth();
    }
}
