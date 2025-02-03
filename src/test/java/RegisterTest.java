import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static config.TestConfig.TestingSetup.*;
import static config.TestConfig.TestingSetup.url;

public class RegisterTest {
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
    public void testRegisterSuccess() {
        String randomEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        String randomPassword = UUID.randomUUID().toString().substring(0, 10);
        WebDriverWait wait = new WebDriverWait(driver, 3);

        // Perform sign-up process (same as before)
        MobileElement sign_up_button = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button")
                )
        );
        sign_up_button.click();

        MobileElement emailField = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"))
        );
        emailField.sendKeys(randomEmail);
        MobileElement passwordField = driver.findElement(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
        passwordField.sendKeys(randomPassword);
        MobileElement confirmPasswordField = driver.findElement(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[3]"));
        confirmPasswordField.sendKeys(randomPassword);

        MobileElement register_button = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"))
        );
        register_button.click();

        MobileElement nextScreenElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button")
        ));

        MobileElement drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button"))
        );
        drawer_button.click();

        MobileElement sign_out = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View[2]"))
        );
        FirebaseService.deleteUserFromFirebase(randomEmail);
        Assert.assertNotNull(nextScreenElement, "Register failed, composable screen did not change.");
        sign_out.click();
    }

    @Test(priority = 2)
    public void testRegisterFailure() {
        String existingEmail = "d@g.com";
        String password = "123456";
        WebDriverWait wait = new WebDriverWait(driver, 3);
        MobileElement sign_up_button = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button")
                )
        );
        sign_up_button.click();
        MobileElement emailField = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"))
        );
        emailField.sendKeys(existingEmail);
        MobileElement passwordField = driver.findElement(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
        passwordField.sendKeys(password);
        MobileElement confirmPasswordField = driver.findElement(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[3]"));
        confirmPasswordField.sendKeys(password);
        MobileElement register_button = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"))
        );
        register_button.click();
        boolean registerFailed;
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
                    )
            );
            registerFailed = true;
        } catch (NoSuchElementException | TimeoutException e) {
            registerFailed = false;
        }
        if(!registerFailed){
            FirebaseService.deleteUserFromFirebase(existingEmail);
        }
        Assert.assertTrue(registerFailed, "Register was expected to fail, but it succeeded.");
    }
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
