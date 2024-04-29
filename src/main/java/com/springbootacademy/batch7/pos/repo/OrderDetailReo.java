package com.springbootacademy.batch7.pos.repo;


import com.springbootacademy.batch7.pos.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailReo extends JpaRepository<OrderDetails, Integer> {
}
