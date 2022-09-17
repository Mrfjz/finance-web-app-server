package mrfjz.application.financewebappserver.services;

import mrfjz.application.financewebappserver.models.Instrument;
import mrfjz.application.financewebappserver.models.InstrumentSummary;

import java.util.Set;

public interface InstrumentService {
    Set<Instrument> getInstruments();

    InstrumentSummary getInstrumentSummary(String symbol);

    Set<InstrumentSummary> getInstrumentsSummary();
}
