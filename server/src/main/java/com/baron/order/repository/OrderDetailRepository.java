package com.baron.order.repository;

import com.baron.order.dataobject.OrderDetail;
import com.baron.order.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-6:18 PM
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
