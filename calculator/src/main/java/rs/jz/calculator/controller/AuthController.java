package rs.jz.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.jz.calculator.exeptions.ApiRequestException;
import rs.jz.calculator.model.userDto.AuthRequest;
import rs.jz.calculator.model.userDto.AuthResponse;
import rs.jz.calculator.model.userDto.RegisterDto;
import rs.jz.calculator.repositories.UserRepository;
import rs.jz.calculator.services.UserService;
import rs.jz.calculator.services.implementatiton.UserDetailServiceImp;
import rs.jz.calculator.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImp userDetailServiceImp;
    private final JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, UserDetailServiceImp userDetailServiceImp, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailServiceImp = userDetailServiceImp;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authReq){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        } catch (Exception e){
            throw new ApiRequestException("Wrong password or username!");
        }

        UserDetails userDetails = userDetailServiceImp.loadUserByUsername(authReq.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")
    public String register(@Validated @RequestBody RegisterDto registerDto) {
        if(registerDto.getUsername().length() == 0 || registerDto.getUsername().equals(""))
            throw new ApiRequestException("The username can't be null!");

        if(registerDto.getPassword().length() == 0 || registerDto.getPassword().equals(""))
            throw new ApiRequestException("The password can't be null!");

        if(registerDto.getUserType().equals(null) || registerDto.getUserType().equals(""))
            throw new ApiRequestException("The user type can't be null!");

        if(userRepository.findByUsername(registerDto.getUsername()) != null)
            throw new ApiRequestException("The username is taken!");

        if(registerDto.getPassword().length() < 6)
            throw new ApiRequestException("The password must be at least 6 characters long!");

        if(!registerDto.getPassword().matches(".*\\d.*"))
            throw new ApiRequestException("The password must contain a number!");

        return userService.register(registerDto);
    }
}
