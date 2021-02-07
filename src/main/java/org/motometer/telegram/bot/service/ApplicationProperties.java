package org.motometer.telegram.bot.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("app.config")
public class ApplicationProperties {

  String jdbcUrl;
  String userName;
  String password;
  String telegramToken;
  String telegramApi;
}
