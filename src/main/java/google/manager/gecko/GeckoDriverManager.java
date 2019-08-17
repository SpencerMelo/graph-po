package google.manager.gecko;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;

public class GeckoDriverManager extends DriverChain {

    private GeckoDriverService geckoDriverService;

    public GeckoDriverManager() {
        super(IdBrowsers.FIREFOX);
    }

    @Override
    protected void startService() {
        try {
            geckoDriverService = new GeckoDriverService.Builder()
                    .usingDriverExecutable(new File("geckodriver.exe"))
                    .usingAnyFreePort()
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected WebDriver createWebDriver() {
        return new FirefoxDriver(geckoDriverService);
    }

    @Override
    protected void stopService() {
        if (geckoDriverService != null && geckoDriverService.isRunning()) {
            geckoDriverService.stop();
        }
    }
}
