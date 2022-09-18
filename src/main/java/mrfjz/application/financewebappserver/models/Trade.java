package mrfjz.application.financewebappserver.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trades")
public class Trade extends BaseEntity{
    @Column(name = "price", nullable = false)
    private float price;
    @Column(name = "quantity", nullable = false)
    private float quantity;
    @Column(name = "side", nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeSide side;

    @CreatedDate
    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    public Trade(float price, float quantity, TradeSide side, Account account, Instrument instrument) {
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.account = account;
        this.instrument = instrument;
    }
}
