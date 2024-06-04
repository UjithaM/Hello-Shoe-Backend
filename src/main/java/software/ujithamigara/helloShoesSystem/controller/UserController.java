package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<?> signUp(@Validated @RequestBody SignUp signUpReq, BindingResult bindingResult) {
        logger.info("Received sign-up request for user: {}", signUpReq.getEmail());
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            JwtAuthResponse jwtAuthResponse = authenticationService.signUp(signUpReq);
            if (jwtAuthResponse == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you don't have permission to sign up or user already exists.");
            } else {
                return ResponseEntity.ok(jwtAuthResponse);
            }
        } catch (Exception exception) {
            logger.error("Error during sign-up: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Sign-up unsuccessful.\nMore Details\n" + exception);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Validated @RequestBody SignIn signInReq, BindingResult bindingResult) {
        logger.info("Received sign-in request for user: {}", signInReq.getEmail());
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            JwtAuthResponse jwtAuthResponse = authenticationService.signIn(signInReq);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (Exception exception) {
            logger.error("Error during sign-in: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Sign-in unsuccessful.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        logger.info("Received refresh token request");
        try {
            JwtAuthResponse jwtAuthResponse = authenticationService.refreshToken(refreshToken);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (Exception exception) {
            logger.error("Error during token refresh: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Token refresh unsuccessful.\nMore Details\n" + exception);
        }
    }
}
