import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTransaction(String transactionData) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, transactionData);
        System.out.println("Transaction sent: " + transactionData);
    }
}