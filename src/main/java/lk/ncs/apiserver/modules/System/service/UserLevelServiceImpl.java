package lk.ncs.apiserver.modules.System.service;

import lk.ncs.apiserver.core.common.APIResponse;
import lk.ncs.apiserver.core.common.BadRequestException;
import lk.ncs.apiserver.core.common.Error;
import lk.ncs.apiserver.modules.System.dto.UserLevelDto;
import lk.ncs.apiserver.modules.System.entity.SystemUserLevel;
import lk.ncs.apiserver.modules.System.repository.SystemUserLevelRepository;
import lk.ncs.apiserver.modules.System.validator.UserLevelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserLevelServiceImpl implements UserLevelService{
    @Autowired
    private SystemUserLevelRepository systemUserLevelRepository;
    @Autowired
    private UserLevelValidator userLevelValidator;
    @Override
    public APIResponse CreateUserLevel(UserLevelDto userLevelDto) {
        APIResponse apiResponse = new APIResponse();

        List<Error> errors = userLevelValidator.ValidateCreateUserLevel(userLevelDto);
        if(errors.size() > 0) {
            throw new BadRequestException(" [ UserInfoServiceImpl -> addUser ] ",errors);
        }
        // Trim the user level name before validation
        String levelName = userLevelDto.getUserLevelName().trim();
        if(systemUserLevelRepository.existsByUserLevelNameAndStatusNot3(levelName)){
            apiResponse.setError("UserLevelName '" + levelName + "' already exists among active user levels");
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to add UserLevelName");
            apiResponse.setStatus(400); // Bad Request
            return apiResponse;
        }
        try {
            SystemUserLevel systemUserLevel = new SystemUserLevel();
            systemUserLevel.setUserLevelId(userLevelDto.getUserLevelId());
            systemUserLevel.setUserLevelName(levelName);
            systemUserLevel.setStatus(1);
            systemUserLevel.setCreatedBy(1);
            SystemUserLevel savedUserLevel = systemUserLevelRepository.save(systemUserLevel);
            apiResponse.setError("no errors");
            apiResponse.setData(savedUserLevel.getUserLevelId()); // Set the ID in the response
            apiResponse.setIsSuccessfull(true);
            apiResponse.setMessage("UserLevelName added successfully! ");
            apiResponse.setStatus(200);
        } catch (Exception e) {
            apiResponse.setError("Error saving UserLevelName to the repository: " + e.getMessage());
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to add user");
            apiResponse.setStatus(500);
            e.printStackTrace();
        }
        return apiResponse;
    }



    @Override
    public APIResponse UpdateUserLevel(UserLevelDto userLevelDto) {
        APIResponse apiResponse = new APIResponse();
        List<Error> errors = userLevelValidator.ValidateUpdateUserLevel(userLevelDto);
        if(errors.size() > 0) {
            throw new BadRequestException(" [ UserLevelName -> Update UserLevelName ] ",errors);
        }

        Optional<SystemUserLevel> storeSystemUserLevel =  this.systemUserLevelRepository.findById(userLevelDto.getUserLevelId());
        if(storeSystemUserLevel.isEmpty()){
            apiResponse.setError("Invalid UserLevel ID'");
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to update UserLevelName");
            apiResponse.setStatus(400); // Bad Request
            return apiResponse;
        }

        SystemUserLevel existingUserLevel = storeSystemUserLevel.get();
        if(existingUserLevel.getStatus() == 3) { // Check if the user level is deleted (status 3)
            apiResponse.setError("UserLevel is already deleted");
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to update UserLevelName");
            apiResponse.setStatus(400); // Bad Request
            return apiResponse;
        }

        // Trim the user level name before validation
        String newLevelName = userLevelDto.getUserLevelName().trim();
        if(systemUserLevelRepository.existsByUserLevelNameAndStatusNot3(newLevelName) &&
                !existingUserLevel.getUserLevelName().equals(newLevelName)) { // Exclude the current record from the check
            apiResponse.setError("UserLevelName already exists among active user levels");
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to update UserLevelName");
            apiResponse.setStatus(400); // Bad Request
            return apiResponse;
        }

        try {
            existingUserLevel.setUserLevelName(newLevelName); // Update the user level name
            systemUserLevelRepository.save(existingUserLevel); // Save the updated user level
            apiResponse.setData(existingUserLevel.getUserLevelId());
            apiResponse.setError("no errors");
            apiResponse.setIsSuccessfull(true);
            apiResponse.setMessage("UserLevelName updated successfully! ");
            apiResponse.setStatus(200);
        } catch (Exception e) {
            apiResponse.setError("Error saving UserLevelName to the repository: " + e.getMessage());
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to update UserLevelName");
            apiResponse.setStatus(500);
            e.printStackTrace();
        }
        return apiResponse;
    }

    @Override
    public APIResponse SoftDeleteUserLevel(int userLevelId) {
        APIResponse apiResponse = new APIResponse();
        Optional<SystemUserLevel> optionalSystemUserLevel = this.systemUserLevelRepository.findById(userLevelId);
        if(optionalSystemUserLevel.isEmpty()){
            apiResponse.setError("Invalid UserLevel ID");
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to delete UserLevel");
            apiResponse.setStatus(400); // Bad Request
            return apiResponse;
        }

        SystemUserLevel systemUserLevel = optionalSystemUserLevel.get();
        systemUserLevel.setStatus(3); // Mark as deleted (soft delete)

        try {
            this.systemUserLevelRepository.save(systemUserLevel);
            apiResponse.setData(userLevelId);
            apiResponse.setError("no errors");
            apiResponse.setIsSuccessfull(true);
            apiResponse.setMessage("UserLevel deleted successfully!");
            apiResponse.setStatus(200);
        } catch (Exception e) {
            apiResponse.setError("Error deleting UserLevel: " + e.getMessage());
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to delete UserLevel");
            apiResponse.setStatus(500);
            e.printStackTrace();
        }
        return apiResponse;
    }

    @Override
    public APIResponse GetAllActiveUserLevels() {
        APIResponse apiResponse = new APIResponse();
        try {
            List<SystemUserLevel> activeUserLevels = this.systemUserLevelRepository.findByStatusNot(3);
            apiResponse.setData(activeUserLevels);
            apiResponse.setError("no errors");
            apiResponse.setIsSuccessfull(true);
            apiResponse.setMessage("Active UserLevels retrieved successfully!");
            apiResponse.setStatus(200);
        } catch (Exception e) {
            apiResponse.setError("Error retrieving active UserLevels: " + e.getMessage());
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to retrieve active UserLevels");
            apiResponse.setStatus(500);
            e.printStackTrace();
        }
        return apiResponse;
    }

    @Override
    public APIResponse GetActiveUserLevelByID(int userLevelId) {
        APIResponse apiResponse = new APIResponse();
        try {
            Optional<SystemUserLevel> storeSystemUserLevel =  this.systemUserLevelRepository.findByUserLevelIdAndStatusNot(userLevelId, 3);
            if (storeSystemUserLevel.isPresent()) {
                apiResponse.setData(storeSystemUserLevel.get());
                apiResponse.setError("no errors");
                apiResponse.setIsSuccessfull(true);
                apiResponse.setMessage("UserLevel retrieved successfully! ");
                apiResponse.setStatus(200);
            } else {
                apiResponse.setError("UserLevel with ID " + userLevelId + " not found or inactive");
                apiResponse.setIsSuccessfull(false);
                apiResponse.setMessage("Failed to retrieve UserLevel!");
                apiResponse.setStatus(404); // Not Found
            }
        } catch (Exception e) {
            apiResponse.setError("Error retrieving UserLevel: " + e.getMessage());
            apiResponse.setIsSuccessfull(false);
            apiResponse.setMessage("Failed to retrieve UserLevel!");
            apiResponse.setStatus(500);
            e.printStackTrace();
        }
        return apiResponse;
    }
}
