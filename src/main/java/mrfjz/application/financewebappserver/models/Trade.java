package mrfjz.application.financewebappserver.models;

import lombok.Data;
import mrfjz.application.financewebappserver.enums.TradeSide;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
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
}
