package lk.ncs.apiserver.modules.System.repository;

import lk.ncs.apiserver.modules.System.entity.SystemMenuRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMenuRuleRepository extends JpaRepository<SystemMenuRule, Integer> {
}
