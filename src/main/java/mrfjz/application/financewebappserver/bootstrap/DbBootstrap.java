package mrfjz.application.financewebappserver.bootstrap;

import lombok.extern.slf4j.Slf4j;
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
    private final TransactionRepository transactionRepository;


    public DbBootstrap(UserRepository userRepository, AccountRepository accountRepository, PositionRepository positionRepository, InstrumentRepository instrumentRepository, TradeRepository tradeRepository, QuoteRepository quoteRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.positionRepository = positionRepository;
        this.instrumentRepository = instrumentRepository;
        this.tradeRepository = tradeRepository;
        this.quoteRepository = quoteRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user = new User("Adam", "Savage", "bob123@hotmail.com", "123456");
        Account account = new Account(user);
        user.setAccount(account);
        userRepository.save(user);

        Transaction transaction = new Transaction(1000, TransactionSide.DEPOSIT, account);
        transactionRepository.save(transaction);

        Instrument instrument = new Instrument("AAPL", "Apple", InstrumentType.STOCK, 1, 2, "");
        instrumentRepository.save(instrument);

        Position position = new Position(100, account, instrument);
        positionRepository.save(position);

        Trade trade = new Trade(1, 100, TradeSide.BUY, account, instrument);
        tradeRepository.save(trade);

        quoteRepository.save(new Quote(1, Instant.parse("2019-10-01T08:25:24.00Z"), instrument));
        quoteRepository.save(new Quote(2, Instant.parse("2019-10-02T08:25:24.00Z"), instrument));
    }
}
