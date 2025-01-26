@Component
public class SecurityMiddleware implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (!validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    private boolean validateToken(String token) {
        // Validasi logika token, misalnya JWT atau OAuth
        return token != null && token.startsWith("Bearer ");
    }
}
