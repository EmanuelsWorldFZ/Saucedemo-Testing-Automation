package com.saucedemo.utils;

import com.microsoft.playwright.Page;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ScreenshotOnFailure implements TestWatcher {
  private final Supplier<Page> pageSupplier;
  private final Path outputDirectory;

  public ScreenshotOnFailure(Supplier<Page> pageSupplier, Path outputDirectory) {
    this.pageSupplier = pageSupplier;
    this.outputDirectory = outputDirectory;
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    Page page = pageSupplier.get();
    if (page == null || page.isClosed()) {
      return;
    }

    try {
      Files.createDirectories(outputDirectory);
    } catch (IOException exception) {
      throw new UncheckedIOException("Unable to create screenshot directory", exception);
    }

    String fileName = context.getDisplayName().replaceAll("[^a-zA-Z0-9.-]", "_");
    page.screenshot(new Page.ScreenshotOptions()
        .setPath(outputDirectory.resolve(fileName + ".png"))
        .setFullPage(true));
  }
}
