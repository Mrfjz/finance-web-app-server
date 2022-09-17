package mrfjz.application.financewebappserver.services;

import mrfjz.application.financewebappserver.models.Instrument;
import mrfjz.application.financewebappserver.models.InstrumentSummary;
import mrfjz.application.financewebappserver.models.InstrumentType;
import mrfjz.application.financewebappserver.repositories.InstrumentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InstrumentServiceImpl implements InstrumentService {
    private final InstrumentRepository instrumentRepository;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Set<Instrument> getInstruments() {
        Set<Instrument> instruments = new HashSet<>();
        instrumentRepository.findAll().iterator().forEachRemaining(instruments::add);
        return instruments;
    }

    @Override
    public InstrumentSummary getInstrumentSummary(String symbol) {
        Optional<Instrument> instrument = instrumentRepository.findBySymbol(symbol);
        return instrument.map(InstrumentSummary::new).orElse(null);
    }

    @Override
    public Set<InstrumentSummary> getInstrumentsSummary(String type) {
        InstrumentType instrumentType;
        try {
            instrumentType = InstrumentType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return null;
        }
        Set<Instrument> instruments = new HashSet<>();
        instrumentRepository.findAllByType(instrumentType).iterator().forEachRemaining(instruments::add);
        Set<InstrumentSummary> instrumentSummaries = new HashSet<>();
//        Exclude quotes property
        instruments.stream().map(InstrumentSummary::new).peek(InstrumentSummary::clearQuotes).forEach(instrumentSummaries::add);

        return instrumentSummaries;
    }
}
