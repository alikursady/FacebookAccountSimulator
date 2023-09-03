import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;
import java.util.logging.Logger;

public class FacebookPage {
    private WebDriver driver;
    private By signUpButton = By.xpath("//a[@data-testid='open-registration-form-button']");
    private By firstNameInput = By.xpath("//input[@name='firstname']");
    private By lastNameInput = By.xpath("//input[@name='lastname']");
    private By emailInput = By.xpath("//input[@name='reg_email__']");
    private By emailConfirmationInput = By.xpath("//input[@name='reg_email_confirmation__']");
    private By passwordInput = By.xpath("//input[@id='password_step_input']");
    private By dayDropdown = By.xpath("//select[@id='day']");
    private By monthDropdown = By.xpath("//select[@id='month']");
    private By yearDropdown = By.xpath("//select[@id='year']");
    private static final Logger logger = Logger.getLogger(FacebookPage.class.getName());
    private By genderOption = By.xpath("//label[.='Erkek']");
    private By signUpFinalButton = By.xpath("//button[@name='websubmit']");

    private static final int WAIT_TIMEOUT = 10;

    public FacebookPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignUp() {
        try {
            driver.findElement(signUpButton).click();
        } catch (Exception e) {
            logger.severe("clickSignUp metodu sırasında hata: " + e.getMessage());
        }
    }

    public void enterRandomTurkishName() {
        try {
            String randomName = RandomTurkishNameGenerator.getRandomName();
            new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
            driver.findElement(firstNameInput).sendKeys(randomName);
        } catch (Exception e) {
            logger.severe("enterRandomTurkishName metodu sırasında hata: " + e.getMessage());
        }
    }

    public void enterRandomTurkishLastName() {
        try {
            String randomLastName = RandomTurkishNameGenerator.getRandomLastName();
            new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
            driver.findElement(lastNameInput).sendKeys(randomLastName);
        } catch (Exception e) {
            logger.severe("enterRandomTurkishLastName metodu sırasında hata: " + e.getMessage());
        }
    }

    public void enterEmail(String email) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            driver.findElement(emailInput).sendKeys(email);
            new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(emailConfirmationInput));
            driver.findElement(emailConfirmationInput).sendKeys(email);
        } catch (Exception e) {
            logger.severe("enterEmail metodu sırasında hata: " + e.getMessage());
        }
    }

    public void enterPassword() {
        try {
            String password = PasswordGenerator.generatePassword();
            new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
            driver.findElement(passwordInput).sendKeys(password);
            logger.info("Oluşturulan şifre: " + password);
        } catch (Exception e) {
            logger.severe("enterPassword metodu sırasında hata: " + e.getMessage());
        }
    }

    public void selectBirthdate() {
        try {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int birthYear = currentYear - 20;

            WebElement dayElement = new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(dayDropdown));
            dayElement.click();
            Select daySelect = new Select(dayElement);
            daySelect.selectByIndex(1);

            WebElement monthElement = new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(monthDropdown));
            monthElement.click();
            Select monthSelect = new Select(monthElement);
            monthSelect.selectByIndex(1);

            WebElement yearElement = new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(yearDropdown));
            yearElement.click();

            // JavaScriptExecutor kullanarak doğrudan yılı seçme
            String jsSelectYear = "arguments[0].value='" + birthYear + "';";
            ((JavascriptExecutor) driver).executeScript(jsSelectYear, yearElement);

        } catch (Exception e) {
            logger.severe("selectBirthdate metodu sırasında hata: " + e.getMessage());
        }
    }



    private static class RandomTurkishNameGenerator {
        private static String[] names = {"Ahmet", "Mehmet", "Ayşe", "Fatma", "Ali", "Hasan", "Ezgi"};
        private static String[] lastNames = {"Yılmaz", "Kaya", "Demir", "Çelik", "Koç", "Öztürk", "Kara"};

        public static String getRandomName() {
            java.util.Random random = new java.util.Random();
            int index = random.nextInt(names.length);
            return names[index];
        }

        public static String getRandomLastName() {
            java.util.Random random = new java.util.Random();
            int index = random.nextInt(lastNames.length);
            return lastNames[index];
        }
    }

    private static class PasswordGenerator {
        public static String generatePassword() {
            String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String specialCharacters = "!&";
            String finalString = upperAlphabet + lowerAlphabet + numbers + specialCharacters;

            java.util.Random random = new java.util.Random();

            StringBuilder password = new StringBuilder();

            password.append(upperAlphabet.charAt(random.nextInt(upperAlphabet.length())));
            password.append(lowerAlphabet.charAt(random.nextInt(lowerAlphabet.length())));
            password.append(numbers.charAt(random.nextInt(numbers.length())));
            password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

            for(int i = 4; i < 10; i++) {
                password.append(finalString.charAt(random.nextInt(finalString.length())));
            }

            return password.toString();
        }
    }
    public void selectGender() {
        try {
            WebElement genderElement = new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(genderOption));
            genderElement.click();
        } catch (Exception e) {
            logger.severe("selectGender metodu sırasında hata: " + e.getMessage());
        }
    }

    public void clickFinalSignUp() {
        try {
            WebElement signUpBtn = new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(signUpFinalButton));
            signUpBtn.click();
        } catch (Exception e) {
            logger.severe("clickFinalSignUp metodu sırasında hata: " + e.getMessage());
        }
    }

}
