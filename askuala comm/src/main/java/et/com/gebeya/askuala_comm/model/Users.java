package et.com.gebeya.askuala_comm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;


import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
@Builder


public class Users extends BaseModel{
    @JsonIgnoreProperties(ignoreUnknown = true)
//    @NotBlank(message = "UserName is mandatory")
    @Column(name = "username")
    private String userName;
//    @Size(min = 6, max = 16)
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
//    @NotBlank
    private Authority role;
    private Integer roleId;

    public static class UsersBuilder {
        private boolean isActive;

        public UsersBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Users build() {
            Users users = new Users();
            users.setIsActive(this.isActive);
            users.setUserName(this.userName);
            users.setPassword(this.password);
            users.setRole(this.role);
            users.setRoleId(this.roleId);
            return users;
        }
    }



}
