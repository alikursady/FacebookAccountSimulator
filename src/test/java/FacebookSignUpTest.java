import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**

            eglendim


 */
public class FacebookSignUpTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Temp-mail.io
        driver.get("https://temp-mail.io/tr");
        TempMailPage tempMailPage = new TempMailPage(driver);
        String email = tempMailPage.getEmailAddress();

        // Yeni sekme açma
        ((JavascriptExecutor) driver).executeScript("window.open();");

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        // Facebook gir
        driver.get("https://www.facebook.com");
        FacebookPage facebookPage = new FacebookPage(driver);
        facebookPage.enterEmailOrPhone(email);
        facebookPage.enterPasswordAndSubmit();

    }
}
