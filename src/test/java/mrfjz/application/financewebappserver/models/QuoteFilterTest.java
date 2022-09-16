package mrfjz.application.financewebappserver.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class QuoteFilterTest {
    private QuoteFilter quoteFilter;

    private Quote newQuote(float price, String datetime) {
        var quote = new Quote();
        quote.setPrice(price);
        quote.setTimestamp(Instant.parse(datetime));
        return quote;
    }

    @BeforeEach
    void setUp() {
        List<Quote> quotes = new ArrayList<>();
        quotes.add(newQuote(6, "2000-02-02T06:00:00.00Z"));
        quotes.add(newQuote(1, "1999-01-01T00:00:00.00Z"));
        quotes.add(newQuote(2, "2000-01-01T00:00:00.00Z"));
        quotes.add(newQuote(3, "2000-02-01T00:00:00.00Z"));
        quotes.add(newQuote(4, "2000-02-01T06:00:00.00Z"));
        quotes.add(newQuote(5, "2000-02-02T00:00:00.00Z"));
        quoteFilter = new QuoteFilter(quotes);
    }

    @Test
    void getLastQuote() {
        var quote = quoteFilter.getLastQuote();
        assert quote.getPrice() == 6f;
    }

    @Test
    void getIntraDayQuotes() {
        var quotes = quoteFilter.getIntraDayQuotes();
        assert quotes.size() == 2;
        assert quotes.get(0).getPrice() == 5;
        assert quotes.get(1).getPrice() == 6;
    }

    @Test
    void getPrevDayQuotes() {
        var quotes = quoteFilter.getPrevDayQuotes();
        assert quotes.size() == 2;
        assert quotes.get(0).getPrice() == 3;
        assert quotes.get(1).getPrice() == 4;
    }

    @Test
    void getIntraYearQuotes() {
        var quotes = quoteFilter.getIntraYearQuotes();
        assert quotes.size() == 5;
        assert quotes.get(0).getPrice() == 2;
        assert quotes.get(1).getPrice() == 3;
        assert quotes.get(2).getPrice() == 4;
        assert quotes.get(3).getPrice() == 5;
        assert quotes.get(4).getPrice() == 6;
    }
}