package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;
import utils.ConfigReader;
import utils.DriverUtils;

import java.time.Duration;

public class LinkExternalAccountPage extends BasePage {
    WebDriver driver = DriverUtils.getDriver();
    WebDriverWait drWait = new WebDriverWait(driver, 5, 250);

    @FindBy(css = "h1#page-title")
    WebElement externalAccountTitle;
    @FindBy(xpath = "//div[@class=\"page-title\"]/h1[@id=\"page-title\"]")
    WebElement viewExternalAccountsTitle;
    @FindBy(xpath = "//span[contains(text(), \"no banking providers available\")]")
    WebElement errorMessage;
    @FindBy(xpath = "//div[@class=\"row\"]/div/div/form/div[1]/div[2]/div/label")
    WebElement openBankingProviders;
    @FindBy(css = "select#bankId")
    WebElement providersDropDown;
    @FindBy(xpath = "//div[@class=\"content mt-3\"]/div/div/div/div/form/div/div[2]/div/select/option")
    WebElement noBankingProviders;
    @FindBy(xpath = "//div[@class=\"row\"]/div/div/form/div[1]/div[3]/div/label")
    WebElement userName;
    @FindBy(xpath = "//input[@id=\"username\"]")
    WebElement userNameInputField;
    @FindBy(xpath = "//div[@class=\"row\"]/div/div/form/div[1]/div[4]/div/label")
    WebElement password;
    @FindBy(xpath = "//input[@id=\"password\"]")
    WebElement passWordInputField;
    @FindBy(xpath = "//div[@class=\"card-footer\"]/button[@type=\"submit\"]")
    WebElement submitBtn;
    @FindBy(xpath = "//div[@class=\"card-footer\"]/button[@type=\"reset\"]")
    WebElement resetBtn;
    @FindBy(xpath = "//div[@id=\"emptyAccounts\"]/div/div")
    WebElement alertNoLinkedAccounts;
    @FindBy(xpath = "//div[@id=\"emptyAccounts\"]/div/div/div[3]/a/button")
    WebElement alertContinueButton;


//    public void openProviders(){
//        providersDropDownOptions.selectByVisibleText(ConfigReader.getProperty("invalid.provider"));
//    }

    String primaryWindow = driver.getWindowHandle();

    public void verifyLinkExternalAccountPage() {

        Assertions.assertTrue(externalAccountTitle.isDisplayed());
        Assertions.assertTrue(openBankingProviders.isDisplayed());
        Assertions.assertTrue(providersDropDown.isDisplayed());
        Assertions.assertTrue(userName.isDisplayed());
        Assertions.assertTrue(userNameInputField.isDisplayed());
        Assertions.assertTrue(password.isDisplayed());
        Assertions.assertTrue(passWordInputField.isDisplayed());
        Assertions.assertTrue(submitBtn.isDisplayed());
        Assertions.assertTrue(resetBtn.isDisplayed());
    }

    public void selectOpenProvider() {
        Select providersDropDownOptions = new Select(providersDropDown);
        providersDropDownOptions.selectByVisibleText("Bank of America");
    }

    public void noOpenBankingProvider() {
        Select providersDropDownOptions = new Select(providersDropDown);
        providersDropDownOptions.selectByVisibleText("---------- Select Provider -----------");
    }

    public void noOpenBankingOptions() {
        Assertions.assertTrue(noBankingProviders.getText().equals(ConfigReader.getProperty("invalid.noprovideroptions")), "Open Banking Providers exist.");
    }

    public void reEnterUserNameAndPassword(String str1, String str2) {
        userNameInputField.sendKeys(ConfigReader.getProperty(str1));
        passWordInputField.sendKeys(ConfigReader.getProperty(str2));
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public void clickReset() {
        resetBtn.click();
    }

    public void verifyNewPage() {
        Assertions.assertTrue(!driver.getWindowHandle().equals(primaryWindow), "Link External Account information has not been submitted.");
    }

    public void verifyNoNewPage() {
        Assertions.assertTrue(driver.getWindowHandle().equals(primaryWindow), "You are on a new page.");
    }

    public void verifyErrorMessage() {
        Assertions.assertTrue(errorMessage.isDisplayed());
    }

    public void verifyResetInformationFields() {
        Assertions.assertTrue(userNameInputField.getText().isEmpty());
        Assertions.assertTrue(passWordInputField.getText().isEmpty());
    }

    public void verifyViewExternalAccountsPage() {
        Assertions.assertTrue(viewExternalAccountsTitle.isDisplayed());
    }

    public void verifyAlertNoExternalAccounts() {
        Assertions.assertTrue(alertNoLinkedAccounts.isDisplayed(), "The pop up is not displayed");
    }
    public void alertContinueButton(){
        alertContinueButton.click();
    }
}
