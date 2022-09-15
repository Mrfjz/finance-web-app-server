package mrfjz.application.financewebappserver.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"user", "transactions", "positions", "trades"}, callSuper = false)
@Table(name = "accounts")
public class Account extends BaseEntity{
    @Column(name = "cash")
    private Float cash = 0f;

    @OneToOne(mappedBy = "account")
    private User user;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Position> positions = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Trade> trades = new HashSet<>();
}
