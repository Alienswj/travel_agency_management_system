package cn.edu.cuit.travel_agency_management_system.controller.backstage;

import cn.edu.cuit.travel_agency_management_system.entity.RespBean;
import cn.edu.cuit.travel_agency_management_system.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @GetMapping("/forbidden_user")
    public RespBean forbidden(@RequestParam String To){
        if(mailService.sendForbiddenMail(To))
            return RespBean.ok("已封禁");
        else
            return RespBean.error("发生错误，请重试！");
    }
}
