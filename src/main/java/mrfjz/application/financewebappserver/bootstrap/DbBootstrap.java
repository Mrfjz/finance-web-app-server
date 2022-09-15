package mrfjz.application.financewebappserver.bootstrap;

import lombok.extern.slf4j.Slf4j;
import mrfjz.application.financewebappserver.models.InstrumentType;
import mrfjz.application.financewebappserver.models.TradeSide;
import mrfjz.application.financewebappserver.models.TransactionSide;
import mrfjz.application.financewebappserver.models.*;
import mrfjz.application.financewebappserver.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public class DbBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PositionRepository positionRepository;
    private final InstrumentRepository instrumentRepository;
    private final TradeRepository tradeRepository;
    private final QuoteRepository quoteRepository;

    public DbBootstrap(UserRepository userRepository, AccountRepository accountRepository, PositionRepository positionRepository, InstrumentRepository instrumentRepository, TradeRepository tradeRepository, QuoteRepository quoteRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.positionRepository = positionRepository;
        this.instrumentRepository = instrumentRepository;
        this.tradeRepository = tradeRepository;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user = new User();
        user.setFirstName("Adam");
        user.setLastName("Savage");
        user.setEmail("bob123@hotmail.com");
        user.setPassword("123456");

        Account account = new Account();
        user.setAccount(account);
        account.setUser(user);

        Transaction transaction = new Transaction();
        transaction.setSide(TransactionSide.DEPOSIT);
        transaction.setAmount(1000);
        account.getTransactions().add(transaction);
        transaction.setAccount(account);

        userRepository.save(user);

        Instrument instrument = new Instrument();
        instrument.setSymbol("AAPL");
        instrument.setName("Apple");
        instrument.setType(InstrumentType.STOCK);
        instrument.setLot(1);
        instrument.setPrecision(2);
        instrumentRepository.save(instrument);

        Position position = new Position();
        position.setQuantity(100);
        position.setAccount(account);
        position.setInstrument(instrument);
        positionRepository.save(position);

        Trade trade = new Trade();
        trade.setAccount(account);
        trade.setSide(TradeSide.BUY);
        trade.setQuantity(100);
        trade.setPrice(1);
        trade.setInstrument(instrument);
        tradeRepository.save(trade);

        Quote quote = new Quote();
        quote.setInstrument(instrument);
        quote.setPrice(1);
        quote.setTimestamp(Instant.parse("2019-10-01T08:25:24.00Z"));
        quoteRepository.save(quote);
    }
}
