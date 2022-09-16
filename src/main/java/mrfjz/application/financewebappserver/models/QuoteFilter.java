package mrfjz.application.financewebappserver.models;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteFilter {
    private final List<Quote> quotes;

    public QuoteFilter(List<Quote> quotes) {
        this.quotes = quotes.stream().sorted().collect(Collectors.toList());
    }

    private static Instant getQuoteDate(Quote quote) {
        return quote.getTimestamp().truncatedTo(ChronoUnit.DAYS);
    }

    private Instant getLastQuoteDate() {
        var quote = getLastQuote();
        if (quote == null)
            return null;

        return QuoteFilter.getQuoteDate(quote);
    }

    public Quote getLastQuote() {
        if (quotes.isEmpty())
            return null;

        return quotes.get(quotes.size() - 1);
    }

    public List<Quote> getIntraDayQuotes() {
        var date = getLastQuoteDate();
        if (date == null)
            return null;

        return quotes.stream().filter(q -> q.getTimestamp().compareTo(date) >= 0).collect(Collectors.toList());
    }

    /**
     * Previous day refer to the previous market open day, i.e. if last quote is Monday,
     * previous day will be Friday instead of Sunday.
     *
     * @return
     */
    public List<Quote> getPrevDayQuotes() {
        var date = getLastQuoteDate();
        if (date == null)
            return null;

        var prevDayQuotes = quotes.stream().filter(q -> q.getTimestamp().compareTo(date) < 0).collect(Collectors.toList());
        if (prevDayQuotes.isEmpty())
            return null;

        var prevDayQuote = prevDayQuotes.get(prevDayQuotes.size() - 1);
        var prevDayDate = QuoteFilter.getQuoteDate(prevDayQuote);

        return prevDayQuotes.stream().filter(q -> q.getTimestamp().compareTo(prevDayDate) >= 0).collect(Collectors.toList());
    }

    /**
     * quotes within 52 weeks.
     *
     * @return
     */
    public List<Quote> getIntraYearQuotes() {
        var date = getLastQuoteDate();
        if (date == null)
            return null;

        var minDate = date.minus(52 * 7, ChronoUnit.DAYS);
        return quotes.stream().filter(q -> q.getTimestamp().compareTo(minDate) >= 0).collect(Collectors.toList());
    }

}
