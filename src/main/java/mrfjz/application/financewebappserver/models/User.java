package mrfjz.application.financewebappserver.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"account"}, callSuper = false)
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
