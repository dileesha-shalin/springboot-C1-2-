package com.springbootacademy.batch7.pos.controller;


import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch7.pos.service.ItemService;
import com.springbootacademy.batch7.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @PostMapping(path="/save")
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//       String message= itemService.saveItem(itemSaveRequestDTO);
//        return "saved";
//    }

    @PostMapping(path="/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message= itemService.saveItem(itemSaveRequestDTO);
        ResponseEntity<StandardResponse> response=new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message), HttpStatus.CREATED
        );
        return response;
    }

    @GetMapping(
            path="/get-by-name",
            params="name"
    )

    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOs=itemService.getItemByNameAndStatus(itemName);
        return itemDTOs;


    }

    @GetMapping(
            path="/get-by-name-with-mapstruct",
            params="name"
    )

    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOs=itemService.getItemByNameAndStatusByMapstruct(itemName);
        return itemDTOs;


    }

    @GetMapping(
            path="/get-all-item-by-status",
            params={"activeStatus","page","size"}
    )

    public ResponseEntity<StandardResponse> getItemByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(value = 50) int size

            ){
        //List<ItemGetResponseDTO> itemDTOs=itemService.getItemByActiveStatus(activeStatus);
        PaginatedResponseItemDTO paginatedResponseItemDTO= itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseItemDTO),
                HttpStatus.OK
        );


    }
}
