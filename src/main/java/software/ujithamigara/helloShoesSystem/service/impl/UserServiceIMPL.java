package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.UserRepo;
import software.ujithamigara.helloShoesSystem.dto.UserDTO;
import software.ujithamigara.helloShoesSystem.service.UserService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    private final Mapping map;

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userRepo.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    @Override
    public void save(UserDTO user) {
        map.toUserDTO(userRepo.save(map.toUserEntity(user)));
    }
}
