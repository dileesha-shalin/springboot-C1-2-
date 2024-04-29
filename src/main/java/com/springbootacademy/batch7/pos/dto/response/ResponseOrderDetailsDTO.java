package com.springbootacademy.batch7.pos.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {

    //customer
    private String customerName;
    private String customerAddress;
    private String contactNumber;

    //order
    private Date date;
    private Double total;
}
