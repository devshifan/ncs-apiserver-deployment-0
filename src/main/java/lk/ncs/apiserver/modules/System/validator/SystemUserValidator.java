package lk.ncs.apiserver.modules.System.validator;

import lk.ncs.apiserver.core.common.Error;
import lk.ncs.apiserver.core.security.AuthRequestDto;
import lk.ncs.apiserver.modules.System.entity.SystemUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemUserValidator {

    public List<Error> ValidateAddUser (SystemUser userInfo) {
        List<Error> errors = new ArrayList<>();

        //validate username
        if(userInfo.getUsername() == null){
            Error error = new Error();
            error.setTarget("username");
            error.setMessage("username is null");
            errors.add(error);
        }

        //validate password
        if(userInfo.getPassword() == null){
            Error error = new Error();
            error.setTarget("password");
            error.setMessage("password is null");
            errors.add(error);
        }

        //validate email
        if(userInfo.getEmail() == null){
            Error error = new Error();
            error.setTarget("email");
            error.setMessage("email is null");
            errors.add(error);
        }

        //validate role
        if(userInfo.getRoles() == null){
            Error error = new Error();
            error.setTarget("roles");
            error.setMessage("roles is null");
            errors.add(error);
        }

        return errors;
    }

    public List<Error> ValidateUserLogin(AuthRequestDto authRequestDto) {
        List<Error> errors = new ArrayList<>();

        //validate username
        if(authRequestDto.getUsername() == null){
            Error error = new Error();
            error.setTarget("username");
            error.setMessage("username is null");
            errors.add(error);
        }

        //validate password
        if(authRequestDto.getPassword() == null){
            Error error = new Error();
            error.setTarget("password");
            error.setMessage("password is null");
            errors.add(error);
        }

        return errors;
    }
}
