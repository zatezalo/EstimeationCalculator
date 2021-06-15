package rs.jz.calculator.services.implementatiton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.jz.calculator.exeptions.ApiRequestException;
import rs.jz.calculator.model.MyUser;
import rs.jz.calculator.model.userDto.RegisterDto;
import rs.jz.calculator.repositories.UserRepository;
import rs.jz.calculator.services.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String register(RegisterDto registerDto) {
        MyUser user = userRepository.findByUsername(registerDto.getUsername());
        if (user != null) {
            throw new ApiRequestException("That user with the username " + user.getUsername() + " already exists!!!");
        } else {
            MyUser u = new MyUser();
            u.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            u.setUsername(registerDto.getUsername());
            u.setUserType(registerDto.getUserType());

            userRepository.save(u);

            return "User Created!!";
        }
    }
}
