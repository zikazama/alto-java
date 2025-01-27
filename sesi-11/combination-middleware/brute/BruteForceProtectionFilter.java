@Component
public class BruteForceProtectionFilter extends OncePerRequestFilter {

    private final Map<String, Integer> loginAttempts = new ConcurrentHashMap<>();
    private static final int MAX_ATTEMPTS = 5;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();

        if ("/login".equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
            int attempts = loginAttempts.getOrDefault(clientIP, 0);

            if (attempts >= MAX_ATTEMPTS) {
                response.sendError(HttpServletResponse.SC_TOO_MANY_REQUESTS, "Too many failed login attempts");
                return;
            }

            try {
                filterChain.doFilter(request, response);
            } catch (AuthenticationException e) {
                loginAttempts.put(clientIP, attempts + 1);
                throw e;
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
