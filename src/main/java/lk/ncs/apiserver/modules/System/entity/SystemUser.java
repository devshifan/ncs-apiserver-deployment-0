package lk.ncs.apiserver.modules.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SystemUser")
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",length = 50, nullable = false)
    private int userId;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 50, nullable = false)
    private String firstname;
    @Column(length = 50, nullable = false)
    private String lastname;
    @Column(name = "user_level_id",length = 50, nullable = false)
    private int userLevelId;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 20, nullable = false, unique = true)
    private String nic;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 20)
    private String roles;
    @Column(length = 20)
    private int status;
}
