package mrfjz.application.financewebappserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@ToString(exclude = {"instrument"})
@JsonIgnoreProperties(value = {"instrument"})
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
