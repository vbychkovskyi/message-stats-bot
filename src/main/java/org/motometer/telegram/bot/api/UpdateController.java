package org.motometer.telegram.bot.api;

import org.motometer.telegram.bot.service.WebHookListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UpdateController {

  private final WebHookListener webHookListener;

  @PostMapping("/updates")
  public void onUpdate(@RequestBody String update) {
    webHookListener.onUpdate(update);
  }
}
