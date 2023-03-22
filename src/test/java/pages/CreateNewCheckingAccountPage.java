package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.jupiter.api.Assertions;
import utils.DriverUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateNewCheckingAccountPage extends BasePage{

    @FindBy (css = "div.page-title #page-title")
    WebElement newCheckingAccountTitle;
    @FindBy (xpath = "//*[text()='Select Checking Account Type']")
    WebElement selectCheckingType;
    @FindBy (xpath = "//*[@for='Standard Checking']//*[@type='radio']")
    WebElement standardCheckingRadio;
    @FindBy (xpath = "//*[@for='Interest Checking']//*[@type='radio']")
    WebElement interestCheckingRadio;
    @FindBy (xpath = "//*[text()='Select Account Ownership']")
    WebElement selectAccountOwnership;
    @FindBy (xpath = "//*[@for='Individual']//input")
    WebElement individualRadio;
    @FindBy (xpath = "//*[@for='Joint']//input")
    WebElement jointRadio;
    @FindBy (xpath = "//*[text()='Account Name']")
    WebElement accountName;
    @FindBy (xpath = "//div[@class='row form-group']//*[@id='name']")
    WebElement accountNameBar;
    @FindBy (xpath = "//*[text()='Initial Deposit Amount']")
    WebElement initialDeposit;
    @FindBy (xpath = "//div[@class='row form-group']//*[@id='openingBalance']")
    WebElement initialDepositBar;
    @FindBy (css = "#newCheckingSubmit")
    WebElement submitBtn;
    @FindBy (xpath = "//*[text()='Successfully created new Standard Checking account named Practice05']")
    WebElement confirmationMsg;
    @FindBy (css = "#newCheckingReset")
    WebElement resetBtn;



    public void verifyTheUserIsOnNewTab(){
        WebDriver driver = DriverUtils.getDriver();
        String primeWindowHandling = driver.getWindowHandle();
        List<String> listOfWindows = new ArrayList<>(driver.getWindowHandles());
        for(String handles: listOfWindows){
            if(!handles.equals(primeWindowHandling)){
                driver.switchTo().window(handles);
            }
        }
        Assertions.assertTrue(newCheckingAccountTitle.isDisplayed());
    }

    public void verifyAllRelatedLabelsAndRadios(){
        Assertions.assertTrue(selectCheckingType.isDisplayed());
        Assertions.assertTrue(standardCheckingRadio.isDisplayed());
        Assertions.assertTrue(interestCheckingRadio.isDisplayed());
        Assertions.assertTrue(selectAccountOwnership.isDisplayed());
        Assertions.assertTrue(individualRadio.isDisplayed());
        Assertions.assertTrue(jointRadio.isDisplayed());
        Assertions.assertTrue(accountName.isDisplayed());
        Assertions.assertTrue(accountNameBar.isDisplayed());
        Assertions.assertTrue(initialDeposit.isDisplayed());
        Assertions.assertTrue(initialDepositBar.isDisplayed());
    }

    public void userProvidesValidInformation(String newCheckingAccountName){
        standardCheckingRadio.click();
        individualRadio.click();
        accountNameBar.sendKeys(newCheckingAccountName);
        initialDepositBar.sendKeys("200");
    }

    public void clickSubmitBtn(){
        submitBtn.click();
    }

    public void verifyConfirmation(){
        Assertions.assertTrue(confirmationMsg.isDisplayed(),"Creation of new Standard Checking account failed");
    }

    public void clickResetBtn(){
        resetBtn.click();
    }

    public void verifyEverythingIsBlank(){
        Assertions.assertTrue(!standardCheckingRadio.isSelected());
        Assertions.assertTrue(!interestCheckingRadio.isSelected());
        Assertions.assertTrue(!individualRadio.isSelected());
        Assertions.assertTrue(!jointRadio.isSelected());
        Assertions.assertTrue(accountNameBar.getText().equals(""));
        Assertions.assertTrue(initialDepositBar.getText().equals(""));
    }
}
