package org.motometer.telegram.bot.service;

import lombok.Data;

@Data
public class UserStats {

  private final String userName;
  private final long messageCount;
}
