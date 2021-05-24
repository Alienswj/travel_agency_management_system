package cn.edu.cuit.travel_agency_management_system.service.impl;

import cn.edu.cuit.travel_agency_management_system.entity.User;
import cn.edu.cuit.travel_agency_management_system.entity.UserRole;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.UserWithRole;
import cn.edu.cuit.travel_agency_management_system.mapper.UserMapper;
import cn.edu.cuit.travel_agency_management_system.service.RoleService;
import cn.edu.cuit.travel_agency_management_system.service.UserRoleService;
import cn.edu.cuit.travel_agency_management_system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 时伟建
 * @since 2021-04-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;

    final static Logger logger= LoggerFactory.getLogger(UserService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Map<String,Object> map=new HashMap<>();
//        map.put("username",username);
//        List<User> list=userMapper.selectByMap(map);
//        if(list==null){
//            System.out.println("null");
//            throw new UsernameNotFoundException("用户名不存在");
//        }else{
//            logger.info(list.toString());
//        }
//        User user=list.get(0);
//
//
//        UserWithRole ur=new UserWithRole();
//        BeanUtils.copyProperties(user,ur);
//        map.clear();
//        map.put("uid",list.get(0).getId());
//        String rid=userRoleMapper.selectByMap(map).get(0).getRid();
//
//        ur.setRoles(Arrays.asList(roleMapper.selectById(rid)));
        UserWithRole ur=new UserWithRole();
        User user=this
                .query()
                .eq("username",username)
                .one();
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }else{
            logger.info(user.toString());
        }
        BeanUtils.copyProperties(user,ur);

        UserRole userRole=userRoleService
                .query()
                .eq("uid",user.getId())
                .one();

        ur.setRoles(roleService
                .query()
                .eq("id",userRole.getRid())
                .list());
        return ur;
    }
}
