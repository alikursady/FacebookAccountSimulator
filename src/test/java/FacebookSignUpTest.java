import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.logging.Logger;

public class FacebookSignUpTest {
    private static final Logger logger = Logger.getLogger(FacebookSignUpTest.class.getName());

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Temp-mail.io
            driver.get("https://temp-mail.io/tr");
            TempMailPage tempMailPage = new TempMailPage(driver);
            String email = tempMailPage.getEmailAddress();

            // Yeni sekme açma
            ((JavascriptExecutor) driver).executeScript("window.open();");
            driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

            // Facebook'a giriş yap
            driver.get("https://www.facebook.com");
            FacebookPage facebookPage = new FacebookPage(driver);

            facebookPage.clickSignUp();
            facebookPage.enterRandomTurkishName();
            facebookPage.enterRandomTurkishLastName();

            // Alınan e-postayı Facebook'a gir
            facebookPage.enterEmail(email);
            facebookPage.enterPassword();
            facebookPage.selectBirthdate();
            facebookPage.selectGender();
            facebookPage.clickFinalSignUp();

        } catch (Exception e) {
            logger.severe("Bir hata oluştu: " + e.getMessage());
        }


    }
}
