package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.jupiter.api.Assertions;
import utils.ConfigReader;
import utils.DriverUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateNewSavingsAccountPage extends BasePage {
    Faker faker = new Faker();

    @FindBy(css = "div.page-title #page-title")
    WebElement createSavingsTitle;
//########NEW SAVINGS OPTIONS ELEMENTS##########
    @FindBy(xpath = "//strong[contains(text(), \"Select Savings Account Type\")]")
    WebElement selectSavingsAccountType;
    @FindBy(xpath = "//input[@id=\"Savings\"]")
    WebElement savingsRadioBtn;
    @FindBy(xpath = "//input[@id=\"Money Market\"]")
    WebElement moneyMarketRadioBtn;
    @FindBy(xpath = "//strong[contains(text(), \"Select Account Ownership\")]")
    WebElement accountOwnership;
    @FindBy(xpath = "//input[@id=\"Individual\"]")
    WebElement individual;
    @FindBy(xpath = "//input[@id=\"Joint\"]")
    WebElement joint;
    @FindBy(xpath = "//strong[contains(text(), \"Account Name\")]")
    WebElement accountNameTitle;
    @FindBy(xpath = "//input[@id=\"name\"]")
    WebElement accountNameInput;
    @FindBy(xpath = "//strong[contains(text(), \"Initial Deposit\")]")
    WebElement initialDepositTitle;
    @FindBy(xpath = "//input[@id=\"openingBalance\"]")
    WebElement openingBalanceInput;
    @FindBy(xpath = "//button[@id=\"newSavingsSubmit\"]")
    WebElement submitBtn;
    @FindBy(xpath = "//button[@id=\"newSavingsReset\"]")
    WebElement resetBtn;

    public void verifyCreateSavingsPage() {
        WebDriver driver = DriverUtils.getDriver();
        String primeWindowHandling = driver.getWindowHandle();
        List<String> listOfWindows = new ArrayList<>(driver.getWindowHandles());
        for (String handles : listOfWindows) {
            if (!handles.equals(primeWindowHandling)) {
                driver.switchTo().window(handles);
            }
        }
        Assertions.assertTrue(createSavingsTitle.isDisplayed(), "This is not the Create Savings page.");
    }
    public void verifyAllRelatedLabelsAndRadios(){
        Assertions.assertTrue(selectSavingsAccountType.isDisplayed());
        Assertions.assertTrue(savingsRadioBtn.isDisplayed());
        Assertions.assertTrue(moneyMarketRadioBtn.isDisplayed());
        Assertions.assertTrue(individual.isDisplayed());
        Assertions.assertTrue(joint.isDisplayed());
        Assertions.assertTrue(accountNameInput.isDisplayed());
        Assertions.assertTrue(accountOwnership.isDisplayed());
        Assertions.assertTrue(accountNameTitle.isDisplayed());
        Assertions.assertTrue(initialDepositTitle.isDisplayed());
        Assertions.assertTrue(openingBalanceInput.isDisplayed());
        Assertions.assertTrue(submitBtn.isDisplayed());
        Assertions.assertTrue(resetBtn.isDisplayed());
    }
    //########NEW SAVINGS OPTIONS METHODS##########
    public void selectSavings(){
        savingsRadioBtn.click();
    }
    public void selectMoneyMarket(){
        moneyMarketRadioBtn.click();
    }
    public void individualAccount(){
        individual.click();
    }
    public void jointAccount(){
        joint.click();
    }
    public void accountName(){
        String name = faker.name().lastName() + " Savings";
        accountNameInput.sendKeys(ConfigReader.getProperty("savings.name"));
//        return name;
    }
    public void openBalance(){
        openingBalanceInput.sendKeys(ConfigReader.getProperty("savings.deposit"));
    }
    public void submitNewSavingsInformation() {
        submitBtn.click();
    }
    public void resetNewSavingsInformation() {
        resetBtn.click();
    }
    public void verifyNewSavingsInformationErased() {
        Assertions.assertTrue(accountNameInput.getText().isEmpty());
        Assertions.assertTrue(openingBalanceInput.getText().isEmpty());
    }

}
