@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtVerificationFilter jwtVerificationFilter;
    private final RoleBasedAccessControlFilter rbacFilter;
    private final BruteForceProtectionFilter bruteForceFilter;

    public SecurityConfig(JwtVerificationFilter jwtVerificationFilter,
                          RoleBasedAccessControlFilter rbacFilter,
                          BruteForceProtectionFilter bruteForceFilter) {
        this.jwtVerificationFilter = jwtVerificationFilter;
        this.rbacFilter = rbacFilter;
        this.bruteForceFilter = bruteForceFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtVerificationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(rbacFilter, JwtVerificationFilter.class)
            .addFilterBefore(bruteForceFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
