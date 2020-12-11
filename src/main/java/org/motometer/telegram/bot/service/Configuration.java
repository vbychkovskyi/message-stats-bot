package org.motometer.telegram.bot.service;

import java.util.ServiceLoader;

import javax.inject.Singleton;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.client.BotBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import io.micronaut.context.annotation.Factory;
import lombok.RequiredArgsConstructor;

@Factory
@RequiredArgsConstructor
public class Configuration {

  private final ApplicationProperties applicationProperties;

  @Singleton
  Bot bot() {
    return BotBuilder.defaultBuilder()
      .token(applicationProperties.getTelegramToken())
      .apiHost(applicationProperties.getTelegramApi())
      .build();
  }

  @Singleton
  Gson gson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
      gsonBuilder.registerTypeAdapterFactory(factory);
    }
    return gsonBuilder.create();
  }

  @Singleton
  WebHookListener webHookListener() {
    final PostgresRepository postgresRepository = new PostgresRepository(applicationProperties);

    final ExceptionSafeListener l1 = new ExceptionSafeListener(new UpdateListener(postgresRepository));

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
