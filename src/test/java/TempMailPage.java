import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TempMailPage {
    private WebDriver driver;
    private By emailField = By.xpath("//input[@id='email']");

    public TempMailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmailAddress() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(emailField).getAttribute("value");
    }
}
