package br.com.danillotparreira.cursomc.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import br.com.danillotparreira.cursomc.model.PagamentoComBoleto;
import br.com.danillotparreira.cursomc.model.PagamentoComCartao;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
      public void configure(ObjectMapper objectMapper) {
        objectMapper.registerSubtypes(PagamentoComBoleto.class);
        objectMapper.registerSubtypes(PagamentoComCartao.class);
        super.configure(objectMapper);
      }
    };
    return builder;
  }
}
