package org.motometer.telegram.bot.api;

import javax.inject.Inject;

import org.motometer.telegram.bot.service.WebHookListener;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UpdateController {

  @Inject
  private final WebHookListener webHookListener;

  @Post("/updates")
  public void onUpdate(@Body String update) {
    webHookListener.onUpdate(update);
  }
}
