package cn.edu.cuit.travel_agency_management_system.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class TripWithInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String title;

    private String destination;

    private Integer price;

    private String features;

    private String introduction;

    private String fee;

    private String notice;

    private String date;

    private Integer quantity;

    private Integer number;

}
