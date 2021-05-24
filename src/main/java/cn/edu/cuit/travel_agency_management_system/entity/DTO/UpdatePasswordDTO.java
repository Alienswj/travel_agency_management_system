package cn.edu.cuit.travel_agency_management_system.entity.DTO;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String id;
    private String newPassword;
    private String passwordAgain;
}
