package org.motometer.telegram.bot.service;

public interface WebHookListener {

  void onUpdate(final String update);
}
