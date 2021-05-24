package cn.edu.cuit.travel_agency_management_system.entity.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class OrdersWithTripDTO {
    private String oid;
    private String tid;
    private String title;
    private String destination;
    private Integer price;
    private Date date;
    private Integer quantity;
}
