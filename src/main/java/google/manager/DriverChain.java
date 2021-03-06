package google.manager;

import org.openqa.selenium.WebDriver;

public abstract class DriverChain {
    private DriverChain next;
    private IdBrowsers idBrowser;

    public DriverChain(IdBrowsers id) {
        next = null;
        idBrowser = id;
    }

    public void setNext(DriverChain driver) {
        if (next == null)
            next = driver;
        else
            next.setNext(driver);
    }

    public void quitDriver(WebDriver webDriver) {
        if (null != webDriver) {
            webDriver.quit();
        }
    }

    public WebDriver getWebDriver(IdBrowsers id) throws Exception {
        if (canCreate(id)) {
            startService();
            return createWebDriver();
        } else {
            if (next == null) {
                throw new Exception(id.toString() + " driver not found");
            }
            return next.getWebDriver(id);
        }
    }

    private boolean canCreate(IdBrowsers id) {
        return idBrowser == id;
    }

    protected abstract void startService();

    protected abstract WebDriver createWebDriver();

    protected abstract void stopService();
}
