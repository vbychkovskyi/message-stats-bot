package org.motometer.telegram.bot.service;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.api.Update;

@RequiredArgsConstructor
public class UpdateListener implements WebHookListener {

  private final PostgresRepository postgresRepository;
  private final Gson gson;

  @Override
  public void onUpdate(final String update) {
    final Update parsedUpdate = gson.fromJson(update, Update.class);
    long chatId =     parsedUpdate.message().chat().id();
    String text = parsedUpdate.message().text();
    postgresRepository.saveUpdate(update, chatId, text);
  }
}
