package cn.edu.cuit.travel_agency_management_system.mapper;

import cn.edu.cuit.travel_agency_management_system.entity.DTO.QueryTripDTO;
import cn.edu.cuit.travel_agency_management_system.entity.DTO.TripWithInventory;
import cn.edu.cuit.travel_agency_management_system.entity.Trip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.ManagedBean;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 时伟建
 * @since 2021-04-07
 */
@Mapper
public interface TripMapper extends BaseMapper<Trip> {
    @Select("select * from trip,inventory where trip.id=inventory.tid and trip.destination=#{destination,jdbcType=CHAR} and inventory.date=#{date,jdbcType=DATE} limit #{current},#{pageSize}")
    public List<TripWithInventory> queryTripWithInventory(QueryTripDTO queryTripDTO);
    @Select("select count(*) from trip,inventory where trip.id=inventory.tid and trip.destination=#{destination,jdbcType=CHAR} and inventory.date=#{date,jdbcType=DATE}")
    public int getTotalNumber(Map<String,Object> map);
}
