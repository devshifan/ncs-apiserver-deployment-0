package lk.ncs.apiserver.core.security;

import lk.ncs.apiserver.modules.System.entity.SystemUser;
import lk.ncs.apiserver.modules.System.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> systemUser = systemUserRepository.findByUsername(username);

        return systemUser.map(CustomUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found" + username));
    }
}
