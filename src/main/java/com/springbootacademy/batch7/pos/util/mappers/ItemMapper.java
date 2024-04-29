package com.springbootacademy.batch7.pos.util.mappers;


import com.springbootacademy.batch7.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch7.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch7.pos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);
    ItemGetResponseDTO entityToDTO(Item item);
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemGetResponseDTO> ListDTOToPage(Page<Item> items);
}
