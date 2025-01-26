@Service
public class TokenService {

    public boolean isValid(String token) {
        // Logika validasi token, misalnya decoding JWT
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        // Validasi tambahan
        return true;
    }
}
