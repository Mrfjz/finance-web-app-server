package mrfjz.application.financewebappserver.services;

import mrfjz.application.financewebappserver.models.Instrument;

import java.util.Set;

public interface InstrumentService {
    Set<Instrument> getInstruments();
    Instrument getInstrument(String symbol);
}
