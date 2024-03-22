package lk.ncs.apiserver.modules.System.service;

import lk.ncs.apiserver.core.common.APIResponse;
import lk.ncs.apiserver.modules.System.dto.UserLevelDto;
import lk.ncs.apiserver.modules.System.entity.SystemUserLevel;

public interface UserLevelService {
    public APIResponse CreateUserLevel(UserLevelDto userLevelDto);
    public APIResponse UpdateUserLevel(UserLevelDto userLevelDto);
    public APIResponse SoftDeleteUserLevel(int userLevelId);
    public APIResponse GetAllActiveUserLevels();
    public APIResponse GetActiveUserLevelByID(int userLevelId);
}
