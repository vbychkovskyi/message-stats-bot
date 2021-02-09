package org.motometer.telegram.bot.service;

import java.util.ServiceLoader;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.client.BotBuilder;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import lombok.RequiredArgsConstructor;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
public class Configuration {

  private final ApplicationProperties applicationProperties;

  @Bean
  Bot bot() {
    return BotBuilder.defaultBuilder()
      .token(applicationProperties.getTelegramToken())
      .apiHost(applicationProperties.getTelegramApi())
      .build();
  }

  @Bean
  Gson gson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
      gsonBuilder.registerTypeAdapterFactory(factory);
    }
    return gsonBuilder.create();
  }

  @Bean
  WebHookListener webHookListener() {
    final PostgresRepository postgresRepository = new PostgresRepository(applicationProperties);

    final ExceptionSafeListener l1 = new ExceptionSafeListener(new UpdateListener(postgresRepository,gson()));

    final ExceptionSafeListener l2 = new ExceptionSafeListener(new CommandListener(
      bot(),
      gson(),
      postgresRepository
    ));

    return e -> {
      l1.onUpdate(e);
      l2.onUpdate(e);
    };
  }
}
