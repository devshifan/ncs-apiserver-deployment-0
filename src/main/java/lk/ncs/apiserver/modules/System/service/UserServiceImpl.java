package lk.ncs.apiserver.modules.System.service;

import lk.ncs.apiserver.core.common.APIResponse;
import lk.ncs.apiserver.core.common.BadRequestException;
import lk.ncs.apiserver.core.common.Error;
import lk.ncs.apiserver.core.security.AuthRequestDto;
import lk.ncs.apiserver.core.security.JwtService;
import lk.ncs.apiserver.modules.System.entity.SystemUser;
import lk.ncs.apiserver.modules.System.repository.SystemUserRepository;
import lk.ncs.apiserver.modules.System.validator.SystemUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private SystemUserValidator systemUserValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public APIResponse AddUser(SystemUser systemUser) {
        APIResponse apiResponse = new APIResponse();
        List<Error> errors = systemUserValidator.ValidateAddUser(systemUser);

        if(errors.size() > 0) {
            throw new BadRequestException(" [ UserInfoServiceImpl -> addUser ] ",errors);
        }else{

            if (systemUserRepository.existsByEmail(systemUser.getEmail())) {
                apiResponse.setError("Email '" + systemUser.getEmail() + "' already exists");
                apiResponse.setIsSuccessfull(false);
                apiResponse.setMessage("Failed to add user");
                apiResponse.setStatus(400); // Bad Request
                return apiResponse;
            }

            if (systemUserRepository.existsByNic(systemUser.getNic())) {
                apiResponse.setError("Nic '" + systemUser.getNic() + "' already exists");
                apiResponse.setIsSuccessfull(false);
                apiResponse.setMessage("Failed to add user");
                apiResponse.setStatus(400); // Bad Request
                return apiResponse;
            }

            try {
                systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
                systemUserRepository.save(systemUser);
                apiResponse.setError("no errors");
                apiResponse.setIsSuccessfull(true);
                apiResponse.setMessage("User added successfully! ");
                apiResponse.setStatus(200);
            } catch (Exception e) {
                apiResponse.setError("Error saving user to the repository: " + e.getMessage());
                apiResponse.setIsSuccessfull(false);
                apiResponse.setMessage("Failed to add user");
                apiResponse.setStatus(500);
                e.printStackTrace();
            }
        }

        return apiResponse;
    }
    @Override
    public APIResponse UserLogin(AuthRequestDto authRequestDto){
        APIResponse apiResponse = new APIResponse();
        List<Error> errors = systemUserValidator.ValidateUserLogin(authRequestDto);

        if(errors.size() > 0) {
            throw new BadRequestException(" [ UserInfoServiceImpl -> UserLogin ] ",errors);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));

        if(authentication.isAuthenticated()){
            String jwtToken = jwtService.GenerateToken(authRequestDto.getUsername());
            Map<String, String> responseData = new HashMap<>();
            responseData.put("jwtToken", jwtToken);

            apiResponse.setData(responseData);
            apiResponse.setError("no errors");
            apiResponse.setMessage("User validated successfully! ");
            apiResponse.setIsSuccessfull(true);
            apiResponse.setStatus(200);
        }else{
            throw new UsernameNotFoundException("Invalid User Request!");
        }

        return apiResponse;
    }
}
