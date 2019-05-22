package com.bookimage.parser;

public interface Parser<Entity, DTO> {

    Entity parseFrom(DTO dto);
    DTO parseTo(Entity entity);

}
