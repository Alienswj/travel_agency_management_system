package cn.edu.cuit.travel_agency_management_system.controller.forestage;

import cn.edu.cuit.travel_agency_management_system.entity.RespBean;
import cn.edu.cuit.travel_agency_management_system.entity.User;
import cn.edu.cuit.travel_agency_management_system.entity.UserRole;
import cn.edu.cuit.travel_agency_management_system.service.RoleService;
import cn.edu.cuit.travel_agency_management_system.service.UserRoleService;
import cn.edu.cuit.travel_agency_management_system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags="用户注册接口")
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService urService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @ApiOperation("跳转到注册页面")
    @GetMapping
    public void toRegister(HttpServletResponse response){
        response.setStatus(302);
        response.setHeader("Location","http://localhost:8080/register.html");
    }
    @ApiOperation("注册")
    @PostMapping
    public RespBean register(@RequestBody Map<String, String> params){
        System.out.println(params);
        if(params==null){
            return RespBean.error("未知错误");
        }
        User user=new User();
        user.setUsername(params.get("username"));
        user.setPassword(passwordEncoder.encode(params.get("password")));
        user.setPhone(params.get("phone"));

        if(userService.save(user)){
            String id=userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername())).getId();
            UserRole userRole=new UserRole();
            userRole.setUid(id);
            userRole.setRid("5e5847afb1b74e3a841b55e9706b271b");
            urService.save(userRole);
            return RespBean.ok("注册成功！");
        }else{
            return RespBean.error("注册失败！");
        }
    }
}
