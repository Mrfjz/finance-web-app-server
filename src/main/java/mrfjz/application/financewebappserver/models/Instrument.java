package mrfjz.application.financewebappserver.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mrfjz.application.financewebappserver.enums.InstrumentType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"positions", "trades", "quotes"}, callSuper = false)
@Table(name = "instruments")
public class Instrument extends BaseEntity{
    @Column(name = "symbol", nullable = false)
    private String symbol;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InstrumentType type;
    @Column(name = "precision", nullable = false)
    private Integer precision = 2;
    @Column(name = "lot", nullable = false)
    private Integer lot = 1;

    @OneToMany(mappedBy = "instrument")
    private Set<Position> positions = new HashSet<>();

    @OneToMany(mappedBy = "instrument")
    private Set<Trade> trades = new HashSet<>();

    @OneToMany(mappedBy = "instrument")
    private Set<Quote> quotes = new HashSet<>();
}
