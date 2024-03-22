package lk.ncs.apiserver.modules.System.controller;

import lk.ncs.apiserver.core.common.APIResponse;
import lk.ncs.apiserver.modules.System.dto.UserLevelDto;
import lk.ncs.apiserver.modules.System.entity.SystemUser;
import lk.ncs.apiserver.modules.System.entity.SystemUserLevel;
import lk.ncs.apiserver.modules.System.service.UserLevelService;
import lk.ncs.apiserver.modules.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-level")
public class UserLevelController {
    @Autowired
    private UserLevelService userLevelService;
    @PostMapping("/create-user-level")
    public APIResponse CreateUserLevel(@RequestBody UserLevelDto userLevelDto){
        return  userLevelService.CreateUserLevel(userLevelDto);
    }
    @PutMapping("/update-user-level")
    public APIResponse UpdateUserLevel(@RequestBody UserLevelDto userLevelDto){
        return  userLevelService.UpdateUserLevel(userLevelDto);
    }
    @GetMapping("/all-active-user-levels")
    public APIResponse GetAllActiveUserLevels(){
        return  userLevelService.GetAllActiveUserLevels();
    }
    @GetMapping("/user-level-by-id/{id}")
    public APIResponse GetUserLevelById(@PathVariable("id") Integer userLevelId){
        return  userLevelService.GetActiveUserLevelByID(userLevelId);
    }
    @DeleteMapping("/user-level-by-id/{id}")
    public APIResponse DeleteUserLevelById(@PathVariable("id") Integer userLevelId){
        return  userLevelService.SoftDeleteUserLevel(userLevelId);
    }
}
