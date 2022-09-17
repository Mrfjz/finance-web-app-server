package mrfjz.application.financewebappserver.controllers;

import mrfjz.application.financewebappserver.models.Instrument;
import mrfjz.application.financewebappserver.models.InstrumentSummary;
import mrfjz.application.financewebappserver.services.InstrumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {
    private final InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public Set<Instrument> getInstruments() {
        return instrumentService.getInstruments();
    }

    @GetMapping("/{symbol}")
    public InstrumentSummary getInstrumentSummary(@PathVariable String symbol) {
        return instrumentService.getInstrumentSummary(symbol);
    }

    @GetMapping("/summary")
    public Set<InstrumentSummary> getInstrumentsSummary() {
        return instrumentService.getInstrumentsSummary();
    }
}
