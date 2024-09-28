package sign_up.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sign_up.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT U FROM User U WHERE U.email =:email")
    Optional<User> findByEmail(String email);
    User findByPassword(String password);


    boolean existsByEmail(String email);

}
