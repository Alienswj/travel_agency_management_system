package cn.edu.cuit.travel_agency_management_system.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ChangeOrderDTO {
    private String tid;
    private String oid;

    private Date odate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date ndate;
}
