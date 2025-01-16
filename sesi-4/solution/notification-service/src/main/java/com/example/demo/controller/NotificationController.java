import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) {
        String url = switch (request.getType().toLowerCase()) {
            case "email" -> "http://email-service/email/send";
            case "sms" -> "http://sms-service/sms/send";
            case "push" -> "http://push-service/push/send";
            default -> throw new IllegalArgumentException("Invalid notification type: " + request.getType());
        };
        return restTemplate.postForObject(url, request, String.class);
    }
}

