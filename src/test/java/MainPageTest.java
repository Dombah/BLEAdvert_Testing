import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static config.TestConfig.TestingSetup.*;
import static config.TestConfig.TestingSetup.url;

public class MainPageTest {
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
    public void testAdminDrawerContentCount(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        MobileElement email = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")));
        email.sendKeys("d@g.com");
        MobileElement password = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")));
        password.sendKeys("123456");
        MobileElement signInButton = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")))
                ;
        signInButton.click();
        MobileElement drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button"))
        );
        drawer_button.click();
        MobileElement sign_out = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[7]/android.view.View[2]"))
        );
        MobileElement home_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]"))
        );
        MobileElement scanned_history = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]"))
        );
        MobileElement modify_beacons = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]/android.view.View[2]"))
        );
        MobileElement admin_panel = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View[2]"))
        );
        MobileElement rewards = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[6]/android.view.View[2]"))
        );
        int foundElementsCount = 0;
        if (sign_out != null) foundElementsCount++;
        if (home_button != null) foundElementsCount++;
        if (scanned_history != null) foundElementsCount++;
        if (modify_beacons != null) foundElementsCount++;
        if (admin_panel != null) foundElementsCount++;
        if (rewards != null) foundElementsCount++;
        Assert.assertEquals(foundElementsCount, 6, "Not all elements were found. Expected 6, but found " + foundElementsCount);
        home_button.click();
    }
    @Test(priority = 2)
    public void testAdminDrawerContent(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        MobileElement drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button"))
        );
        drawer_button.click();
        MobileElement scanned_history_button= (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]"))
        );
        scanned_history_button.click();
        MobileElement test_event= (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View"))
        );
        test_event.click();
        MobileElement test_text= (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//android.widget.TextView[@text=\"test\"]"))
        );
        Assert.assertNotNull(test_text, "Test failed: There is no test event");
        drawer_button.click();
        MobileElement modify_beacons_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]/android.view.View[2]"))
        );
        modify_beacons_button.click();
        MobileElement beacon_dropdown = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]"))
        );
        Assert.assertNotNull(beacon_dropdown, "Test failed: There is no beacon inside modify beacons");
        beacon_dropdown.click();
        MobileElement add_beacon_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.Button"))
        );
        add_beacon_button.click();
        MobileElement add_beacon_dialog = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View"))
        );
        Assert.assertNotNull(add_beacon_dialog, "Test failed: The beacon dialog inside modify beacons did not open");
        driver.navigate().back();
        drawer_button.click();
        MobileElement admin_panel_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View[2]"))
        );
        admin_panel_button.click();
        MobileElement add_event_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view." +
                        "View[2]/android.view.View[2]/android.view.View/android.widget.Button"))
        );
        Assert.assertNotNull(add_event_button, "Test failed: The page didn't change to admin panel");
        add_event_button.click();
        MobileElement add_reward_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"))
        );
        Assert.assertNotNull(add_reward_button, "Test failed: The dialog for adding rewards didn't show up");
        add_reward_button.click();
        driver.navigate().back();
        driver.navigate().back();
        drawer_button.click();
        MobileElement rewards_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[6]/android.view.View[2]"))
        );
        rewards_button.click();
        MobileElement rewards_header = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[1]"))
        );
        drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View"))
        );
        Assert.assertNotNull(rewards_header, "Test failed: The screen didn't change to rewards page");
        drawer_button.click();
        MobileElement sign_out = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[7]/android.view.View[2]"))
        );
        sign_out.click();
    }
    @Test(priority = 3)
    public void testUserDrawerCount(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        MobileElement email = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")));
        email.sendKeys("d2@g.com");
        MobileElement password = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")));
        password.sendKeys("123456");
        MobileElement signInButton = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")))
                ;
        signInButton.click();
        MobileElement drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button"))
        );
        drawer_button.click();
        MobileElement sign_out = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View[2]"))
        );
        MobileElement home_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]"))
        );
        MobileElement scanned_history = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]"))
        );
        MobileElement rewards = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]/android.view.View[2]"))
        );
        int foundElementsCount = 0;
        if (sign_out != null) foundElementsCount++;
        if (home_button != null) foundElementsCount++;
        if (scanned_history != null) foundElementsCount++;
        if (rewards != null) foundElementsCount++;
        Assert.assertEquals(foundElementsCount, 4, "Not all elements were found. Expected 6, but found " + foundElementsCount);
        home_button.click();
    }
    @Test(priority = 4)
    public void testUserDrawerContent(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        MobileElement drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button"))
        );
        drawer_button.click();
        MobileElement scanned_history = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]"))
        );
        scanned_history.click();
        MobileElement test_event= (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View"))
        );
        test_event.click();
        MobileElement test_text= (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//android.widget.TextView[@text=\"dobrodosao\"]"))
        );
        Assert.assertNotNull(test_text, "Test failed: There is no test event");
        drawer_button.click();
        MobileElement rewards = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]/android.view.View[2]"))
        );
        rewards.click();
        MobileElement rewards_screen_element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]"))
        );
        Assert.assertNotNull(rewards_screen_element, "Test failed: The screen didn't change to rewards");
        drawer_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View"))
        );
        drawer_button.click();
        MobileElement sign_out = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View[2]"))
        );
        sign_out.click();
    }
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
