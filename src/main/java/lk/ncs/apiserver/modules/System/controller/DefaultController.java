package lk.ncs.apiserver.modules.System.controller;

import jakarta.annotation.PostConstruct;
import lk.ncs.apiserver.core.security.JwtService;
import lk.ncs.apiserver.modules.System.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class DefaultController {
    @Autowired
    private DefaultService defaultService;
    @PostConstruct
    public void CreateDefaultSystemRun() {
        defaultService.CreateDefaultSystemRun();
    }

}
