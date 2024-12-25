package com.example.bank.Controller;
import com.example.bank.Model.MyUser;
import com.example.bank.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(authService.getAllUsers(myUser.getId()));
    }
}
