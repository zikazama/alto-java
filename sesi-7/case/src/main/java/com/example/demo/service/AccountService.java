import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Cacheable(value = "accountBalance", key = "#accountId")
    public AccountBalance getAccountBalance(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @CacheEvict(value = "accountBalance", key = "#accountId")
    public void updateAccountBalance(String accountId, Double newBalance) {
        AccountBalance account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}