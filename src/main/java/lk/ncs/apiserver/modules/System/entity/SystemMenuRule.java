package lk.ncs.apiserver.modules.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SystemMenuRule")
public class SystemMenuRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_rule_id", length = 50, nullable = false)
    private int menuRuleId;
    @Column(name = "menu_id", length = 50, nullable = false)
    private int menuId;
    @Column(name = "menu_name", length = 50, nullable = false)
    private String menuName;
    @Column(name = "parent_menu_id", nullable = false)
    private int parentMenuId;
    @Column(name = "module_id", nullable = false)
    private int moduleId;
    @Column(length = 20)
    private int status;
    @Column(name = "user_level_id",length = 50, nullable = false)
    private int userLevelId;
}
