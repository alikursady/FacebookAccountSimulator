import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

public class TempMailPage {
    private WebDriver driver;
    private By emailField = By.xpath("//input[@id='email']");
    private static final Logger logger = Logger.getLogger(TempMailPage.class.getName());

    public TempMailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmailAddress() {
        try {
            Thread.sleep(3000);
            return driver.findElement(emailField).getAttribute("value");
        } catch (InterruptedException e) {
            logger.severe("getEmailAddress metodu sırasında hata: " + e.getMessage());
            return "";
        }
    }
}
