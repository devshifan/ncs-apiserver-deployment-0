package lk.ncs.apiserver.modules.System.controller;

import lk.ncs.apiserver.core.common.APIResponse;
import lk.ncs.apiserver.core.security.AuthRequestDto;
import lk.ncs.apiserver.modules.System.entity.SystemUser;
import lk.ncs.apiserver.modules.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public APIResponse AddNewUser(@RequestBody SystemUser systemUser){
        return  userService.AddUser(systemUser);
    }
    @PostMapping("/user-login")
    public APIResponse UserLogin (@RequestBody AuthRequestDto authRequestDto) {
        return userService.UserLogin(authRequestDto);
    }
}
