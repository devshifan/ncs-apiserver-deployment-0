package lk.ncs.apiserver.modules.System.service;

import lk.ncs.apiserver.core.common.APIResponse;
import lk.ncs.apiserver.core.security.AuthRequestDto;
import lk.ncs.apiserver.modules.System.entity.SystemUser;

public interface UserService {
    public APIResponse AddUser(SystemUser systemUser);
    public APIResponse UserLogin(AuthRequestDto authRequestDto);
}
