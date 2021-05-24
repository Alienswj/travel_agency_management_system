package cn.edu.cuit.travel_agency_management_system.controller.forestage;

import cn.edu.cuit.travel_agency_management_system.config.VerificationCode;
import cn.edu.cuit.travel_agency_management_system.entity.RespBean;
import cn.edu.cuit.travel_agency_management_system.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @ApiOperation("返回用户未登录的消息")
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录！");
    }

    @GetMapping("/hello")
    public void sayHello(HttpServletRequest request, HttpServletResponse response){
        //response.setStatus(302);
        //response.setHeader("Location","/index.html");
        String names =request.getSession().getAttribute("SPRING_SECURITY_CONTEXT").toString();
        System.out.println(names);
        //return RespBean.error("hello");
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name=user.getName();
        System.out.println(name);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }

}
