package lk.ncs.apiserver.modules.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SystemUserLevel")
public class SystemUserLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_level_id",length = 50, nullable = false)
    private int userLevelId;
    @Column(name = "user_level_name",length = 50, nullable = false)
    private String userLevelName;
    @Column(length = 20)
    private int status;
    @Column(name = "created_by",length = 50, nullable = false)
    private int createdBy;
}
