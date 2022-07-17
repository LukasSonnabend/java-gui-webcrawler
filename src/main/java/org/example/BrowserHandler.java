package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.nio.file.Paths;

public class BrowserHandler {
  private  Playwright playwright;
  public BrowserHandler(){
    playwright = Playwright.create();
  };

  private String validUrl(String url) {
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
      return "http://" + url;
    }
    return url;
  }

  private String baseUrl(String url) {
    String basedUrl = url.substring(0, url.lastIndexOf("/") != 0 ? url.length() : url.indexOf("/")  + 1);
    return basedUrl;
  }

  public void recordFlow(String url, Boolean saveAuth) {
    BrowserType chromium = playwright.chromium();
    // Make sure to run headed.
    Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));


    // Setup context however you like.
    BrowserContext context = browser.newContext();

    if (Paths.get(baseUrl(url)+".json").toFile().exists()) {
      context = browser.newContext(
          new Browser.NewContextOptions().setStorageStatePath(Paths.get(baseUrl(url)+".json")));
    }

    context.route("**/*", route -> route.resume());
    // Pause the page, and start recording manually.
    Page page = context.newPage();
    page.navigate(validUrl(url));
    //page.pause();
    context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(baseUrl(url)+".json")));
    // wait for browser to close.
    //browser.close();


  }

  public String getPageTitel(String url) {
    try (Browser browser = playwright.chromium().launch();) {
      Page page = browser.newPage();
      page.navigate(validUrl(url));
      return page.title();
    } catch (Exception e) {
      return "";
    }
  }
}
