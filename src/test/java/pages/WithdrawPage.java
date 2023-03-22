package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

public class WithdrawPage extends BasePage{
    @FindBy(xpath = "//h1[text()='Withdraw']")
    WebElement withdrawTitle;
    @FindBy (xpath = "//select[@id='selectedAccount']")
    WebElement selectAccountsDropDown;
    @FindBy (xpath = "//small[@id='selectedAccountBalance']")
    WebElement availableBalance;
    @FindBy (xpath = "//*[@class='col col-sm-3']//input")
    WebElement withdrawAmountBar;
    @FindBy (xpath = "//*[text()=' Submit']")
    WebElement submitBtn;

    Logger log = Logger.getLogger("WithdrawPage");
    public void verifyWithdrawPage(){
        Assertions.assertTrue(withdrawTitle.isDisplayed(), "we are not on withdraw page");
    }

    public void userSelectsWithdrawAccount(String accountValue){
        new Select(selectAccountsDropDown).selectByValue(accountValue);
    }

    public void userCanSeeAvailableBalance(){
        Assertions.assertTrue(availableBalance.isDisplayed());
        log.info("Available "+availableBalance.getText());
    }

    public void userEntersWithdrawAmount(String amount){
        withdrawAmountBar.sendKeys(amount);
    }

    public void userClicksOnSubmitBtn(){
        submitBtn.click();
    }
}
