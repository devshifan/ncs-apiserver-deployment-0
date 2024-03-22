package lk.ncs.apiserver.modules.System.service;

import jakarta.transaction.Transactional;
import lk.ncs.apiserver.core.security.JwtService;
import lk.ncs.apiserver.modules.System.entity.SystemMenu;
import lk.ncs.apiserver.modules.System.entity.SystemMenuRule;
import lk.ncs.apiserver.modules.System.entity.SystemUser;
import lk.ncs.apiserver.modules.System.entity.SystemUserLevel;
import lk.ncs.apiserver.modules.System.repository.SystemMenuRepository;
import lk.ncs.apiserver.modules.System.repository.SystemMenuRuleRepository;
import lk.ncs.apiserver.modules.System.repository.SystemUserLevelRepository;
import lk.ncs.apiserver.modules.System.repository.SystemUserRepository;
import lk.ncs.apiserver.modules.System.validator.SystemUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultServiceImpl implements DefaultService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private SystemMenuRepository systemMenuRepository;
    @Autowired
    private SystemMenuRuleRepository systemMenuRuleRepository;
    @Autowired
    private SystemUserLevelRepository systemUserLevelRepository;

    public void CreateDefaultUsers() {
        var defaultUser = systemUserRepository.findById(1);
        if (defaultUser.isEmpty()) {
            this.initializeDefaultUser();
        }
    }

    @Transactional
    public void initializeDefaultUser() {
        List<SystemUser> systemUsers = new ArrayList<>();
        addSystemUser(systemUsers,"DevShifan","Mohamed Shifan","Samsudeen Mackie","970401016V","devshifan@icloud.com","ROLE_ADMIN","Dark@2@Hunter",1);
        addSystemUser(systemUsers,"admin","Nasik","Nafeel","123456789V","admin@nasik.com","ROLE_ADMIN","321",1);
        //Save Default SystemUsers When Initialize
        systemUserRepository.saveAll(systemUsers);
    }
    private void addSystemUser(List<SystemUser> systemUsers, String username, String firstname, String lastname, String nic, String email, String role, String password, int userLevelId) {
        SystemUser user = new SystemUser();
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setNic(nic);
        user.setEmail(email);
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserLevelId(userLevelId);
        user.setStatus(1);
        systemUsers.add(user);
    }

    public void CreateDefaultUserLevels() {
        var defaultUserLevel = systemUserLevelRepository.findById(1);
        if (defaultUserLevel.isEmpty()) {
            this.initializeDefaultUserLevel();
        }
    }
    @Transactional
    public void initializeDefaultUserLevel() {
        List<SystemUserLevel> systemUserLevels = new ArrayList<>();
        addSystemUserLevel(systemUserLevels,"Administrator");
//        addSystemUserLevel(systemUserLevels,"HR Manager");
//        addSystemUserLevel(systemUserLevels,"Inventory Manager");
//        addSystemUserLevel(systemUserLevels,"Production Manager");
        // Save Default SystemUserLevel When Initialize
        systemUserLevelRepository.saveAll(systemUserLevels);
    }
    private void addSystemUserLevel(List<SystemUserLevel> systemUserLevels, String userLevelName) {
        SystemUserLevel systemUserLevel = new SystemUserLevel();
        systemUserLevel.setUserLevelName(userLevelName);
        systemUserLevel.setCreatedBy(1);
        systemUserLevel.setStatus(1);
        systemUserLevels.add(systemUserLevel);
    }


    public void CreateDefaultSystemMenus() {
        var defaultUser = systemMenuRepository.findAll();
        if (defaultUser.isEmpty()) {
            this.initializeDefaultMenus();
        }
    }
    @Transactional
    public void initializeDefaultMenus() {
        List<SystemMenu> systemMenus = new ArrayList<>();
        // Payroll
        addSystemMenu(systemMenus, "Payroll", 0, 1);
        addSystemMenu(systemMenus, "Time And Attendance", 1, 1);
        addSystemMenu(systemMenus, "Leave Management", 1, 1);
        addSystemMenu(systemMenus, "Salary Management", 1, 1);
        addSystemMenu(systemMenus, "Donation And Deductions", 1, 1);
        addSystemMenu(systemMenus, "Employee Management", 1, 1);

        // Settings
        addSystemMenu(systemMenus, "Settings", 0, 2);
        addSystemMenu(systemMenus, "Factory Management", 7, 2);
        addSystemMenu(systemMenus, "User Management", 7, 2);
        addSystemMenu(systemMenus, "Modules And Privileges", 9, 2);

        // Inventory Management
        addSystemMenu(systemMenus, "Inventory Management", 0, 3);
        addSystemMenu(systemMenus, "Purchase Management", 11, 3);
        addSystemMenu(systemMenus, "Good Received Note", 12, 3);
        addSystemMenu(systemMenus, "Good Return Note", 12, 3);
        addSystemMenu(systemMenus, "Stock Management", 11, 3);
        addSystemMenu(systemMenus, "Material Transfer Note", 15, 3);
        addSystemMenu(systemMenus, "Stock Reconciliation", 11, 3);
        addSystemMenu(systemMenus, "Stock Adjustment", 17, 3);
        addSystemMenu(systemMenus, "Wastage", 17, 3);
        addSystemMenu(systemMenus, "Inventory Settings", 11, 3);
        addSystemMenu(systemMenus, "Supplier Management", 20, 3);
        addSystemMenu(systemMenus, "Measure Types Management", 20, 3);
        addSystemMenu(systemMenus, "Items Management", 20, 3);

        // Production Management
        addSystemMenu(systemMenus, "Production Management", 0, 4);
        addSystemMenu(systemMenus, "Product Creation", 24, 4);
        addSystemMenu(systemMenus, "Production Process", 24, 4);
        addSystemMenu(systemMenus, "Production Plan", 24, 4);
        addSystemMenu(systemMenus, "Production MRN", 24, 4);
        addSystemMenu(systemMenus, "Production Progress", 24, 4);
        addSystemMenu(systemMenus, "Production Transfer Note", 24, 4);

        // Save Default SystemMenus When Initialize
        systemMenuRepository.saveAll(systemMenus);
    }
    private void addSystemMenu(List<SystemMenu> systemMenus, String menuName, int parentMenuId, int moduleId) {
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setMenuName(menuName);
        systemMenu.setParentMenuId(parentMenuId);
        systemMenu.setModuleId(moduleId);
        systemMenu.setStatus(1);
        systemMenus.add(systemMenu);
    }

    public void CreateDefaultSystemMenuRules() {
        var defaultMenuRules = systemMenuRuleRepository.findAll();
        if (defaultMenuRules.isEmpty()) {
            this.initializeDefaultMenuRules();
        }
    }
    @Transactional
    public void initializeDefaultMenuRules() {
        List<SystemMenu> allMenus = this.systemMenuRepository.findAll();
        List<SystemMenuRule> allMenuRules = this.systemMenuRuleRepository.findAll();
        if(!allMenus.isEmpty() && allMenuRules.isEmpty()){
            List<SystemMenuRule> systemMenuRule = new ArrayList<>();

            for (SystemMenu menu : allMenus) {
                SystemMenuRule menuRule = new SystemMenuRule();
                menuRule.setMenuId(menu.getMenuId());
                menuRule.setMenuName(menu.getMenuName());
                menuRule.setParentMenuId(menu.getParentMenuId());
                menuRule.setModuleId(menu.getModuleId());
                menuRule.setStatus(menu.getStatus());
                 menuRule.setUserLevelId(1);
                systemMenuRule.add(menuRule);
            }
            this.systemMenuRuleRepository.saveAll(systemMenuRule);
        }
    }
    @Override
    public void CreateDefaultSystemRun() {
        this.CreateDefaultUsers();
        this.CreateDefaultUserLevels();
        this.CreateDefaultSystemMenus();
        this.CreateDefaultSystemMenuRules();
    }
}
