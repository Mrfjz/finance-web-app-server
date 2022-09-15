package mrfjz.application.financewebappserver.services;

import mrfjz.application.financewebappserver.models.Instrument;
import mrfjz.application.financewebappserver.models.InstrumentSummary;
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
        Set<Instrument> instrumentSet = new HashSet<>();
        instrumentRepository.findAll().iterator().forEachRemaining(instrumentSet::add);
        return instrumentSet;
    }

    @Override
    public Instrument getInstrument(String symbol) {
        Optional<Instrument> instrument = instrumentRepository.findBySymbol(symbol);
        if(instrument.isPresent()){
            InstrumentSummary instrumentSummary = new InstrumentSummary(instrument.get());
        }
        return instrument.orElse(null);
    }
}
