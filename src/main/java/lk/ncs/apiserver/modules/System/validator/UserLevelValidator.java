package lk.ncs.apiserver.modules.System.validator;

import lk.ncs.apiserver.core.common.Error;
import lk.ncs.apiserver.modules.System.dto.UserLevelDto;
import lk.ncs.apiserver.modules.System.entity.SystemUser;
import lk.ncs.apiserver.modules.System.entity.SystemUserLevel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserLevelValidator {
    public List<Error> ValidateCreateUserLevel (UserLevelDto userLevelDto) {
        List<Error> errors = new ArrayList<>();

        //validate userLevelName
        if(userLevelDto.getUserLevelName() == null){
            Error error = new Error();
            error.setTarget("userLevelName");
            error.setMessage("userLevelName is null");
            errors.add(error);
        }
        return errors;
    }
    public List<Error> ValidateUpdateUserLevel (UserLevelDto userLevelDto) {
        List<Error> errors = new ArrayList<>();
        //validate userLevelID
        if(!(userLevelDto.getUserLevelId() > 0) ){
            Error error = new Error();
            error.setTarget("userLevelId");
            error.setMessage("userLevelId is null");
            errors.add(error);
        }
        //validate userLevelName
        if(userLevelDto.getUserLevelName() == null){
            Error error = new Error();
            error.setTarget("userLevelName");
            error.setMessage("userLevelName is null");
            errors.add(error);
        }
        return errors;
    }

}
