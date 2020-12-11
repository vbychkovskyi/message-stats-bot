package org.motometer.telegram.bot.service;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;

@Getter
@ConfigurationProperties("app.config")
public class ApplicationProperties {

  String jdbcUrl;
  String userName;
  String password;
  String telegramToken;
  String telegramApi;
}
