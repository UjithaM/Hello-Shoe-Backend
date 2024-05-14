package software.ujithamigara.helloShoesSystem.service.impl;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.controller.CustomerController;
import software.ujithamigara.helloShoesSystem.dao.EmployeeRepo;
import software.ujithamigara.helloShoesSystem.dao.UserRepo;
import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;
import software.ujithamigara.helloShoesSystem.dto.UserDTO;
import software.ujithamigara.helloShoesSystem.entity.enums.Role;
import software.ujithamigara.helloShoesSystem.reqAndresp.response.JwtAuthResponse;
import software.ujithamigara.helloShoesSystem.reqAndresp.secure.SignIn;
import software.ujithamigara.helloShoesSystem.reqAndresp.secure.SignUp;
import software.ujithamigara.helloShoesSystem.service.AuthenticationService;
import software.ujithamigara.helloShoesSystem.service.EmployeeService;
import software.ujithamigara.helloShoesSystem.service.JWTService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {
    private final UserRepo userRepo;
    private final EmployeeService employeeService;
    private final JWTService jwtService;
    private final Mapping mapping;
    //utils
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userRepo.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
       var generatedToken = jwtService.generateToken(userByEmail);
       return JwtAuthResponse.builder().token(generatedToken).build() ;
    }

    @Override
    public JwtAuthResponse signUp(SignUp signUp) {
        if (checkEmployeeEmail(signUp)) {
            var buildUser = UserDTO.builder()
                    .id(UUID.randomUUID().toString())
                    .email(signUp.getEmail())
                    .firstName(signUp.getFirstName())
                    .lastName(signUp.getLastName())
                    .password(passwordEncoder.encode(signUp.getPassword()))
                    .role(Role.valueOf(signUp.getRole()))
                    .build();
            var savedUser = userRepo.save(mapping.toUserEntity(buildUser));
            var genToken = jwtService.generateToken(savedUser);
            return JwtAuthResponse.builder().token(genToken).build();
        }
        return null;
    }
    private boolean checkEmployeeEmail(SignUp signUp) {
        logger.info("Checking if email is already in use");
        List<EmployeeDTO> emails = employeeService.getAllEmployee();
        for (EmployeeDTO employee : emails) {
            if (employee.getEmail().equals(signUp.getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JwtAuthResponse refreshToken(String accessToken) {
        logger.info("Received refresh token request");
        var userName = jwtService.extractUsername(accessToken);
        var userEntity = userRepo.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.generateToken(userEntity);
        return JwtAuthResponse.builder().token(refreshToken).build();
    }
}
