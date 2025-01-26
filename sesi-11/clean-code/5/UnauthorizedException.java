public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (!tokenService.isValid(token)) {
            throw new UnauthorizedException(SecurityConstants.UNAUTHORIZED_MESSAGE);
        }
        return true;
    }

}
