package sign_up.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "my_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private boolean isAgreedToTerms;


}
