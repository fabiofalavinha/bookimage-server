package com.bookimage.parser;

import com.bookimage.controller.dto.UserDTO;
import com.bookimage.model.User;

public class UserParser implements Parser<User, UserDTO> {

    public User parseFrom(UserDTO dto) {
        final User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDTO parseTo(User user) {
        final UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
