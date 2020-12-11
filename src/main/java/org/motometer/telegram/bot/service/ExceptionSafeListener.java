package org.motometer.telegram.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ExceptionSafeListener implements WebHookListener {

  private final WebHookListener listener;

  @Override
  public void onUpdate(final String update) {
    try {
      listener.onUpdate(update);
    } catch (RuntimeException ex) {
      log.error("ERROR: " + ex.getMessage());
    }
  }
}
