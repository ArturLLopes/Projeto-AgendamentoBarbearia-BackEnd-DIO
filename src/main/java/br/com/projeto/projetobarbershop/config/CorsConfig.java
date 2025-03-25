package br.com.projeto.projetobarbershop.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
public class CorsConfig {

    /**
     * Configura o filtro CORS para permitir requisições de diferentes origens.
     *
     * @return o filtro de CORS registrado com a configuração.
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        // Cria a configuração CORS
        var config = new CorsConfiguration();

        // Permite credenciais em requisições cross-origin
        config.setAllowCredentials(true);

        // Define que qualquer origem é permitida (cuidado com isso em produção)
        config.setAllowedOriginPatterns(Collections.singletonList("*"));

        // Permite qualquer método HTTP (GET, POST, PUT, DELETE, etc.)
        config.setAllowedMethods(Collections.singletonList("*"));

        // Permite qualquer cabeçalho
        config.setAllowedHeaders(Collections.singletonList("*"));

        // Registra a configuração para todas as rotas
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Cria o filtro CORS com a configuração e define a ordem de execução
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsFilter(source));

        // Define que o filtro deve ser executado antes de outros filtros
        bean.setOrder(HIGHEST_PRECEDENCE);

        return bean;
    }
}
