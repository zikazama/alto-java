public class SecurityConstants {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized access";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (!tokenService.isValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(SecurityConstants.UNAUTHORIZED_MESSAGE);
            return false;
        }
        return true;
    }
}
