import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processTransaction(String transactionData) {
        System.out.println("Processing transaction: " + transactionData);
        // Tambahkan logika pemrosesan transaksi di sini.
    }
}