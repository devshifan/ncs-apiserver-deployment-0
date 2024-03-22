package lk.ncs.apiserver.modules.System.repository;

import lk.ncs.apiserver.modules.System.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    Optional<SystemUser> findByUsername(String username);

    Optional<SystemUser> findByEmail(String email);

    Optional<SystemUser> findByNic(String nic);

    boolean existsByEmail(String email);

    boolean existsByNic(String nic);
}
