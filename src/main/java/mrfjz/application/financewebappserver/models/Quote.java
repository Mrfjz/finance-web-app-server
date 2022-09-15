package mrfjz.application.financewebappserver.models;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "quotes")
public class Quote extends BaseEntity{
    @Column(name = "price", nullable = false)
    private float price;
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;
}
