package mrfjz.application.financewebappserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"positions", "trades", "quotes"}, callSuper = false)
@ToString(exclude = {"positions", "trades", "quotes"})
@JsonIgnoreProperties(value = {"positions", "trades", "quotes"})
@Table(name = "instruments")
public class Instrument extends BaseEntity{
    @Column(name = "symbol", nullable = false, unique = true)
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
    @Column(name = "icon_url")
    private String iconUrl;

    public Instrument(String symbol, String name, InstrumentType type, Integer precision, Integer lot, String iconUrl) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.precision = precision;
        this.lot = lot;
        this.iconUrl = iconUrl;
    }

    @OneToMany(mappedBy = "instrument")
    private Set<Position> positions = new HashSet<>();

    @OneToMany(mappedBy = "instrument")
    private Set<Trade> trades = new HashSet<>();

    @OneToMany(mappedBy = "instrument")
    private Set<Quote> quotes = new HashSet<>();
}
