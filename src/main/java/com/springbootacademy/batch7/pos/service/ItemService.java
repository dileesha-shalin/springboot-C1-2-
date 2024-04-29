package com.springbootacademy.batch7.pos.service;

import com.springbootacademy.batch7.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName);

    List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size);
}
