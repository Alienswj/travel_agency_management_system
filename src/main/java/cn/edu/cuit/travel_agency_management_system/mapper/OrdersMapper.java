package cn.edu.cuit.travel_agency_management_system.mapper;

import cn.edu.cuit.travel_agency_management_system.entity.DTO.OrdersWithTripDTO;
import cn.edu.cuit.travel_agency_management_system.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 时伟建
 * @since 2021-04-07
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    @Select("SELECT orders.id as oid,trip.id as tid,trip.title,trip.destination,trip.price,orders.date,orders.quantity\n" +
            "FROM orders,trip where  orders.tid=trip.id and orders.uid=#{id} and orders.status=0")
    List<OrdersWithTripDTO> getMyOrders(String id);

}
