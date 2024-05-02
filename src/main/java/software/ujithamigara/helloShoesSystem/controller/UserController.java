package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.reqAndresp.response.JwtAuthResponse;
import software.ujithamigara.helloShoesSystem.reqAndresp.secure.SignIn;
import software.ujithamigara.helloShoesSystem.reqAndresp.secure.SignUp;
import software.ujithamigara.helloShoesSystem.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody SignUp signUpReq) {
        logger.info("Received sign-up request for user: {}", signUpReq.getEmail());
        return ResponseEntity.ok(authenticationService.signUp(signUpReq));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signInReq) {
        logger.info("Received sign-in request for user: {}", signInReq.getEmail());
        return ResponseEntity.ok(authenticationService.signIn(signInReq));
    }

    @PutMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        logger.info("Received refresh token request");
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
