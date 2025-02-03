import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static config.TestConfig.TestingSetup.*;

public class LoginTest {
    private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", app);

        driver = new AndroidDriver<>(new URI(url).toURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(priority = 1)
    public void testLoginSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        MobileElement email = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")));
        email.sendKeys("d@g.com");
        MobileElement password = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")));
        password.sendKeys("123456");
        MobileElement signInButton = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")))
                ;
        signInButton.click();

        MobileElement nextScreenElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button")
        ));

        MobileElement drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button"))
        );
        drawer_button.click();
        MobileElement sign_out = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[7]/android.view.View[2]"))
        );
        Assert.assertNotNull(nextScreenElement, "Login failed, composable screen did not change.");
        sign_out.click();
    }
    @Test(priority = 2)
    public void testLoginFailure() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        // Enter invalid credentials
        MobileElement email = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
                )
        );
        email.sendKeys("invaliduser@example.com");
        MobileElement password = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
                )
        );
        password.sendKeys("wrong_password");
        MobileElement signInButton = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
                )
        );
        signInButton.click();
        boolean loginFailed;
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
                    )
            );
            loginFailed = true;
        } catch (NoSuchElementException | TimeoutException e) {
            loginFailed = false;
        }
        Assert.assertTrue(loginFailed, "Login was expected to fail, but it succeeded.");
    }
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}