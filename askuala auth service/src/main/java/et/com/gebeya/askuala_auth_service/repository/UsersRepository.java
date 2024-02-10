package et.com.gebeya.askuala_auth_service.repository;



import et.com.gebeya.askuala_auth_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findFirstByUserName(String userName);

    Optional<Users> findByRoleId(Integer id);
}
