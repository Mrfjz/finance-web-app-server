package mrfjz.application.financewebappserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"quoteFilter"})
public class InstrumentSummary implements Serializable {
    private final List<Quote> quotes = new ArrayList<>();
    private final QuoteFilter quoteFilter;
    private String symbol;
    private String name;
    private InstrumentType type;
    private Integer precision;
    private Integer lot;
    private String iconUrl;
    private Float lastPrice;
    private Float priceChange;
    private Float intraDayMinPrice;
    private Float intraDayMaxPrice;
    private Float intraYearMinPrice;
    private Float intraYearMaxPrice;

    public InstrumentSummary(Instrument instrument) {
        symbol = instrument.getSymbol();
        name = instrument.getName();
        type = instrument.getType();
        precision = instrument.getPrecision();
        lot = instrument.getLot();
        iconUrl = instrument.getIconUrl();

        instrument.getQuotes().stream().sorted().iterator().forEachRemaining(quotes::add);
        quoteFilter = new QuoteFilter(quotes);

        lastPrice = calLastPrice();
        priceChange = calPriceChange();
        var intraDayPriceRange = calIntraDayPriceRange();
        if (intraDayPriceRange != null) {
            intraDayMinPrice = intraDayPriceRange[0];
            intraDayMaxPrice = intraDayPriceRange[1];
        }
        var intraYearPriceRange = calIntraYearPriceRange();
        if (intraYearPriceRange != null) {
            intraYearMinPrice = intraYearPriceRange[0];
            intraYearMaxPrice = intraYearPriceRange[1];
        }
    }

    private Float calLastPrice() {
        var quote = quoteFilter.getLastQuote();
        if (quote != null) {
            return quote.getPrice();
        }
        return null;
    }

    private Float calPriceChange() {
        var lastQuote = quoteFilter.getLastQuote();
        var prevDayQuotes = quoteFilter.getPrevDayQuotes();
        if (lastQuote == null || prevDayQuotes == null || prevDayQuotes.isEmpty())
            return null;

        var prevDayQuote = prevDayQuotes.get(prevDayQuotes.size() - 1);
        return lastQuote.getPrice() - prevDayQuote.getPrice();
    }

    private Float[] calIntraDayPriceRange() {
        var intraDayQuotes = quoteFilter.getIntraDayQuotes();
        return calPriceRange(intraDayQuotes);
    }

    private Float[] calIntraYearPriceRange() {
        var intraYear = quoteFilter.getIntraYearQuotes();
        return calPriceRange(intraYear);
    }

    private Float[] calPriceRange(List<Quote> quotes) {
        if (quotes == null || quotes.isEmpty()) {
            return null;
        }

        var minPrice = quotes.get(0).getPrice();
        var maxPrice = minPrice;
        for (var quote : quotes) {
            var price = quote.getPrice();
            minPrice = Math.min(minPrice, price);
            maxPrice = Math.max(maxPrice, price);
        }
        return new Float[]{minPrice, maxPrice};
    }
}
