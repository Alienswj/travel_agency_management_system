package cn.edu.cuit.travel_agency_management_system.controller;


import cn.edu.cuit.travel_agency_management_system.entity.DTO.UpdatePasswordDTO;
import cn.edu.cuit.travel_agency_management_system.entity.RespBean;
import cn.edu.cuit.travel_agency_management_system.entity.User;
import java.util.List;

import cn.edu.cuit.travel_agency_management_system.service.MailService;
import cn.edu.cuit.travel_agency_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 时伟建
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailService mailService;

    @GetMapping("/get_all_user")
    public RespBean getAllUser(){
        List<User> list=userService.list();
        list.forEach(System.out::println);
        return RespBean.ok("成功！",list);
    }

    @PostMapping("/update_info")
    public RespBean updateInfo(@RequestBody User user){
        System.out.println(user);
        if(userService.update().eq("id",user.getId())
                .set(user.getPhone()!=null,"phone",user.getPhone())
                .set(user.getEmail()!=null,"email",user.getEmail())
                .set(user.getName()!=null,"name",user.getName())
                .set(user.getNickname()!=null,"nickname",user.getNickname())
                .set("gender",user.getGender())
                .set(user.getBirthday()!=null,"birthday",user.getBirthday())
                .set(user.getIdNumber()!=null,"id_number",user.getIdNumber())
                .update())
            return RespBean.ok("更新成功！请重新登录以适应更新");
        else
            return RespBean.error("更新失败，请重试！");
    }

    @PostMapping("/update_password")
    public RespBean updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        System.out.println(updatePasswordDTO);
        if(userService.update()
                .eq("id",updatePasswordDTO.getId())
                .set("password",passwordEncoder.encode(updatePasswordDTO.getNewPassword()))
                .update()){
            return RespBean.ok("修改成功！");
        }else{
            return RespBean.error("修改失败，请重试");
        }
    }

    @GetMapping("/get_total_users_number")
    public int getToalUserNumber(){
        return userService.count();
    }

    @Transactional
    @DeleteMapping("/forbidden_user")
    public RespBean forbidden(@RequestBody User user){
        userService.removeById(user.getId());
        mailService.sendForbiddenMail(user.getEmail());
        return RespBean.ok("已封禁！刷新页面以适应更新");
    }
}

