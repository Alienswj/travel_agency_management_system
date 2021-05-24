package cn.edu.cuit.travel_agency_management_system.mapper;

import cn.edu.cuit.travel_agency_management_system.entity.User;

import cn.edu.cuit.travel_agency_management_system.service.SettingService;
import cn.edu.cuit.travel_agency_management_system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private SettingService service;

    @Test
    public void testSelect(){
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        //map条件查询
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","时伟建");
        map.put("phone","13014021030");
        List<User> userList1 = userMapper.selectByMap(map);
        userList1.forEach(System.out::println);
        //分页查询
        Page<User> page=new Page<>(2,2);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user=new User();
        user.setUsername("admindbl2");
        user.setPassword("$2a$10$N2UAMoSON79gZuBdnVKsxuLil35Vx.fkk2m5o/R9HTxo868RhAThG");
        user.setPhone("19981461716");
        System.out.println(userMapper.insert(user));
        testSelect();
    }

    @Test
    public void testUpdate(){
//        User user=new User();
//        user.setId("6a97f0429f953c36adc22b5d1e416f50");
//        user.setName("宝批龙");
//        System.out.println(userMapper.updateById(user));
//        testSelect();
        System.out.println(service.update().eq("id", "6a97f0429f953c36adc22b5d1e416f50").set("push", false).update());
    }

    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper
                .isNotNull("name").between("birthday","1997-1-1","1998-12-31");
        List<User> list=userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testGetUserRole(){
        System.out.println(userService.query());

    }
}
