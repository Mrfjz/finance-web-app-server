package mrfjz.application.financewebappserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"instrument"})
@JsonIgnoreProperties(value = {"instrument", "id"})
@Table(name = "quotes")
public class Quote extends BaseEntity implements Comparable<Quote>{
    @Column(name = "price", nullable = false)
    private float price;
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;


    @Override
    public int compareTo(Quote o) {
        return timestamp.compareTo(o.getTimestamp());
    }

    public Quote(float price, Instant timestamp, Instrument instrument) {
        this.price = price;
        this.timestamp = timestamp;
        this.instrument = instrument;
    }
}
