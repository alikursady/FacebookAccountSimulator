import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FacebookPage {
    private WebDriver driver;
    private By emailField = By.name("email");
    private By passwordField = By.cssSelector("#pass");

    public FacebookPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmailOrPhone(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPasswordAndSubmit() {
        driver.findElement(passwordField).sendKeys("Ahbekanka", Keys.ENTER);
    }
}
