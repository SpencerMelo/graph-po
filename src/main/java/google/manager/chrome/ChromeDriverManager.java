package google.manager.chrome;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class ChromeDriverManager extends DriverChain {

    private ChromeDriverService chromeDriverService;

    public ChromeDriverManager() {
        super(IdBrowsers.CHROME);
    }

    @Override
    protected void startService() {
        try {
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected WebDriver createWebDriver() {
        return new ChromeDriver(chromeDriverService);
    }

    @Override
    protected void stopService() {
        if (chromeDriverService != null && chromeDriverService.isRunning()) {
            chromeDriverService.stop();
        }
    }
}
