package google.manager.internetexplorer;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class InternetExplorerDriverManager extends DriverChain {

    private InternetExplorerDriverService internetExplorerDriverService;

    public InternetExplorerDriverManager() {
        super(IdBrowsers.IEXPLORER);
    }

    @Override
    protected void startService() {
        try {
            internetExplorerDriverService = new InternetExplorerDriverService.Builder()
                    .usingDriverExecutable(new File("IEDriverServer.exe"))
                    .usingAnyFreePort()
                    .build();
            internetExplorerDriverService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected WebDriver createWebDriver() {
        return new InternetExplorerDriver(internetExplorerDriverService);
    }

    @Override
    protected void stopService() {
        if (internetExplorerDriverService != null && internetExplorerDriverService.isRunning()) {
            internetExplorerDriverService.stop();
        }
    }
}
