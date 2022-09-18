package mrfjz.application.financewebappserver.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"account"}, callSuper = false)
@Table(name = "transactions")
public class Transaction extends  BaseEntity{
    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "side", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionSide side;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    public Transaction(float amount, TransactionSide side, Account account) {
        this.amount = amount;
        this.side = side;
        this.account = account;
    }
}
