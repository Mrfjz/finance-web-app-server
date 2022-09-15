package mrfjz.application.financewebappserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"instrument"})
public class InstrumentSummary {
    private String symbol;
    private String name;
    private InstrumentType type;
    private Integer precision;
    private Integer lot;
    private String iconUrl;
    private Float lastPrice;
    private Float change;
    private Float percentChange;
    private Float intraDayMinPrice;
    private Float intraDayMaxPrice;
    private Float intraYearMinPrice;
    private Float intraYearMaxPrice;
    private final Instrument instrument;

    public InstrumentSummary(Instrument instrument) {
        this.instrument = instrument;
        setProperties();
    }

    private void setProperties(){
        symbol = instrument.getSymbol();
        name = instrument.getName();
        type = instrument.getType();
        precision = instrument.getPrecision();
        lot = instrument.getLot();
        iconUrl = instrument.getIconUrl();
    }
}
