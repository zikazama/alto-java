@SpringBootTest
class SecurityMiddlewareTest {

    @Autowired
    private SecurityMiddleware middleware;

    @MockBean
    private TokenService tokenService;

    @Test
    void testValidToken() throws Exception {
        Mockito.when(tokenService.isValid("Bearer valid_token")).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid_token");

        MockHttpServletResponse response = new MockHttpServletResponse();
        boolean result = middleware.preHandle(request, response, new Object());

        Assertions.assertTrue(result);
    }
}
