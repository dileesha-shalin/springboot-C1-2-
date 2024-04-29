package com.springbootacademy.batch7.pos.controller;

import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch7.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch7.pos.service.OrderService;
import com.springbootacademy.batch7.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path="/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
        String id=orderService.addOrder(requestOrderSaveDTO);

        ResponseEntity<StandardResponse> response=new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id + "item saved successfully",id), HttpStatus.CREATED
        );

    }

    @GetMapping(
            params = {"stateType","page","size"},
            path={"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value="stateType") String stateType,
            @RequestParam(value="page") int page,
            @RequestParam(value="size") @Max(50) int size

            ) {

        PaginatedResponseOrderDetails p= null;
        if(stateType.equalsIgnoreCase("active") || stateType.equalsIgnoreCase("incative")){
            boolean status=stateType.equalsIgnoreCase("active") ? true: false;
            p=orderService.getAllOrderDetails(status,page,size);
        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"SUCCESS",p),
                HttpStatus.OK
        );

    }
}
