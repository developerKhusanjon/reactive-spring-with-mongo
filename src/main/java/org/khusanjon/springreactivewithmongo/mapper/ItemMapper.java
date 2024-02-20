package org.khusanjon.springreactivewithmongo.mapper;

import org.khusanjon.springreactivewithmongo.api.ItemResource;
import org.khusanjon.springreactivewithmongo.api.ItemUpdateResource;
import org.khusanjon.springreactivewithmongo.api.NewItemResource;
import org.khusanjon.springreactivewithmongo.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResource toResource(Item item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Item toModel(NewItemResource item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastMOdifiedDate", ignore = true)
    void update(ItemUpdateResource updateResource, @MappingTarget Item item);
}
