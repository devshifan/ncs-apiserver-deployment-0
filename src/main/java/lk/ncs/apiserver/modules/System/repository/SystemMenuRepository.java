package lk.ncs.apiserver.modules.System.repository;

import lk.ncs.apiserver.modules.System.entity.SystemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMenuRepository  extends JpaRepository<SystemMenu, Integer> {
}
