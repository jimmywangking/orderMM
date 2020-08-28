package com.baron.order.repository;

import com.baron.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/***
 @package com.baron.order
 @author Baron
 @create 2020-08-12-6:15 PM
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
