@Component
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {
        System.out.println("Pre Handle method: " + request.getRequestURI());
        return true; // Mengembalikan false akan menghentikan request
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method executed.");
    }
}
