package software.ujithamigara.helloShoesSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.reqAndresp.response.JwtAuthResponse;
import software.ujithamigara.helloShoesSystem.reqAndresp.secure.SignIn;
import software.ujithamigara.helloShoesSystem.reqAndresp.secure.SignUp;
import software.ujithamigara.helloShoesSystem.service.AuthenticationService;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class UserController {
   private final AuthenticationService authenticationService;
    //signup
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody SignUp signUpReq) {
       return ResponseEntity.ok(authenticationService.signUp(signUpReq));
    }
    //signIn
    @GetMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signInReq) {
        return ResponseEntity.ok(authenticationService.signIn(signInReq));
    }
    @PutMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
