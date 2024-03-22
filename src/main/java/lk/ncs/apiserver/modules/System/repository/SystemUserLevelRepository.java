package lk.ncs.apiserver.modules.System.repository;

import lk.ncs.apiserver.modules.System.entity.SystemUserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserLevelRepository extends JpaRepository<SystemUserLevel, Integer> {
    boolean existsByUserLevelName(String userLevelName);
    Optional<SystemUserLevel> findByUserLevelName(String userLevelName);
    List<SystemUserLevel> findByStatusNot(int status);
    Optional<SystemUserLevel> findByUserLevelIdAndStatusNot(int userLevelId, int status);
    @Query("SELECT CASE WHEN COUNT(sl) > 0 THEN true ELSE false END FROM SystemUserLevel sl WHERE sl.userLevelName = ?1 AND sl.status != 3")
    boolean existsByUserLevelNameAndStatusNot3(String userLevelName);
}
