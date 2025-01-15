import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionProducer transactionProducer;

    @PostMapping
    public String createTransaction(@RequestBody String transactionData) {
        transactionProducer.sendTransaction(transactionData);
        return "Transaction submitted.";
    }
}