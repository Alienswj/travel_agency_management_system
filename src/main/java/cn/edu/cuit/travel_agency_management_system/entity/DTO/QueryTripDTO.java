package cn.edu.cuit.travel_agency_management_system.entity.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class QueryTripDTO {
    private String destination;
    private Date date;
    private int current;
    private int pageSize;
    private int total;
}
