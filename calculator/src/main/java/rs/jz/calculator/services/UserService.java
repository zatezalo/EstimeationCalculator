package rs.jz.calculator.services;

import rs.jz.calculator.model.userDto.RegisterDto;

public interface UserService {
    String register(RegisterDto registerDto);
}
