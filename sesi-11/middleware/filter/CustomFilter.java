@Component
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("Request URI: " + httpRequest.getRequestURI());
        
        // Melanjutkan ke filter berikutnya atau ke controller
        chain.doFilter(request, response);
    }
}
