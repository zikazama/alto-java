import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountBalance> getAccountBalance(@PathVariable String accountId) {
        return ResponseEntity.ok(accountService.getAccountBalance(accountId));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<String> updateAccountBalance(@PathVariable String accountId, @RequestBody Double newBalance) {
        accountService.updateAccountBalance(accountId, newBalance);
        return ResponseEntity.ok("Account balance updated successfully");
    }
}