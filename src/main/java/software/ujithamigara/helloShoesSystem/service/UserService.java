package software.ujithamigara.helloShoesSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import software.ujithamigara.helloShoesSystem.dto.UserDTO;

public interface UserService {
    UserDetailsService userDetailsService();
    void save(UserDTO user);
}
