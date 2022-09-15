package mrfjz.application.financewebappserver.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"account", "instrument"}, callSuper = false)
@Table(name = "positions")
public class Position extends BaseEntity{
    @Column(name = "quantity", nullable = false)
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;
}
