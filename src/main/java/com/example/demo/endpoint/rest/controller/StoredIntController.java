package com.example.demo.endpoint.rest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoredIntController {

  private static final Path FILE_PATH = Path.of("/tmp/stored-int.txt");

  @GetMapping("/stored-int")
  public synchronized int getStoredInt() throws IOException {
    if (Files.exists(FILE_PATH)) {
      String content = Files.readString(FILE_PATH).trim();
      return Integer.parseInt(content);
    } else {
      int randomInt = new Random().nextInt(1_000_000);
      Files.writeString(FILE_PATH, String.valueOf(randomInt));
      return randomInt;
    }
  }
}
