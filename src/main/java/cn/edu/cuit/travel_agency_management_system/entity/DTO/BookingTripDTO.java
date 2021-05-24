package cn.edu.cuit.travel_agency_management_system.entity.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class BookingTripDTO {
    private String uid;

    private String name;
    private String phone;
    private String idNumber;
    private String tid;
    private Date date;
}
