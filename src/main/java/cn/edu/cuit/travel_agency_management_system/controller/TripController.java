package cn.edu.cuit.travel_agency_management_system.controller;


import cn.edu.cuit.travel_agency_management_system.entity.DTO.QueryTripDTO;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.TripWithInventory;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.TripsNumberByDestinationDTO;
import cn.edu.cuit.travel_agency_management_system.entity.RespBean;
import cn.edu.cuit.travel_agency_management_system.entity.Trip;
import cn.edu.cuit.travel_agency_management_system.mapper.TripMapper;
import cn.edu.cuit.travel_agency_management_system.service.TripService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 时伟建
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripMapper tripMapper;

    @Autowired
    TripService tripService;

    @GetMapping("/query")
    public RespBean queryTrip(@RequestParam Map<String,Object> queryCondition){

        //Map转POJO
        ObjectMapper objectMapper=new ObjectMapper();
        QueryTripDTO queryTripDTO=objectMapper.convertValue(queryCondition,QueryTripDTO.class);
        queryTripDTO.setCurrent((queryTripDTO.getCurrent()-1)*queryTripDTO.getPageSize());
        System.out.println(queryTripDTO);
        List<TripWithInventory> trips=tripMapper.queryTripWithInventory(queryTripDTO);

        System.out.println(trips);
        if(trips==null){
            return RespBean.error("没有搜索到路线哦");
        }else {
            return RespBean.ok("查询成功！", trips);
        }
    }

    @GetMapping("/get_all_trips")
    public List<Trip> getAllOrders(){
        return tripService.query().list();

    }

    @GetMapping("/getTotalNumber")
    public Integer getTotalNumber(@RequestParam Map<String,Object> queryCondition){

        int total=tripMapper.getTotalNumber(queryCondition);
        System.out.println(total);
        return total;
    }

    @PostMapping("/update_trip")
    public RespBean updateTrip(@RequestBody Trip trip){
        if(tripService.update().eq("id",trip.getId()).update(trip))
            return RespBean.ok("更新成功！");
        else
            return RespBean.error("更新失败！请重试");
    }

    @DeleteMapping("/delete_trip")
    public RespBean deleteTrip(@RequestBody String id){
        id=id.replace("=","");
        if(tripService.removeById(id))
            return RespBean.ok("删除成功！");
        else
            return RespBean.error("删除失败！请重试");
    }

    @PostMapping("/add_trip")
    public RespBean addTrip(@RequestBody Trip trip){
        System.out.println(trip);
        if(tripService.save(trip)){
            return RespBean.ok("添加成功！");
        }else{
            return RespBean.error("添加失败！请重试");
        }
    }

    @GetMapping("/get_total_trips_number")
    public int getTotalTripNumber(){
        return tripService.count();
    }

    @GetMapping("/get_total_trips_number_by_destination")
    public List<TripsNumberByDestinationDTO> getTotalTripsNumberByDestination(){
        QueryWrapper wrapper=new QueryWrapper<TripsNumberByDestinationDTO>();
        wrapper.groupBy("destination");
        wrapper.select("destination,count(*) as number");
        return tripService.list(wrapper);
    }
}

