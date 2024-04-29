package com.springbootacademy.batch7.pos.service.impl;

import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch7.pos.entity.Order;
import com.springbootacademy.batch7.pos.entity.OrderDetails;
import com.springbootacademy.batch7.pos.repo.CustomerRepo;
import com.springbootacademy.batch7.pos.repo.ItemRepo;
import com.springbootacademy.batch7.pos.repo.OrderDetailReo;
import com.springbootacademy.batch7.pos.repo.OrderRepo;
import com.springbootacademy.batch7.pos.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailReo orderDetailReo;

    @Autowired
    private ItemRepo itemRepo;


    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order=new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()

        );
        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails=modelMapper.map(
                    requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {}.getType());

            for(int i=0; i<orderDetails.size(); i++){
                orderDetails.get(i).setOrder(order);
                orderDetails.get(i).setItem(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }
            if(orderDetails.size()>0){
                orderDetailReo.saveAll(orderDetails);


            }
            return "saved";
        }
       return null;
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        return null;
    }
}
