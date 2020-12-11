package org.motometer.telegram.bot.service;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateListener implements WebHookListener {

  private final PostgresRepository postgresRepository;

  @Override
  public void onUpdate(final String update) {
    postgresRepository.saveUpdate(update);
  }
}
