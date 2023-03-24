package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.logging.Logger;

public class ViewCheckingAccountsPage extends BasePage {
    @FindBy(xpath = "//a[text()='View Checking']")
    WebElement viewCheckingOption;
    @FindBy(xpath = "//*[@id='page-title']")
    WebElement pageTitle;
    @FindBy(xpath = "//*[@class='card bg-flat-color-1 text-light']")
    List<WebElement> listOfCheckingAccounts;
    @FindBy(xpath = "//a[@aria-controls='transactionTable']")
    WebElement transactionsTable;
    @FindBy(xpath = "//*[@aria-describedby='transactionTable_info']")
    WebElement transactionDetails;
    @FindBy(xpath = "//*[@class='card bg-flat-color-1 text-light']//label")
    List<WebElement> listOfCheckingAccountsToggleBtn;
    @FindBy(xpath = "//*[@id='transactionTable']//tbody/tr[1]/td[4]")
    WebElement withdrawAmountOnTable;
    @FindBy(xpath = "//*[@id='transactionTable']//tbody/tr[1]/td[5]")
    WebElement newBalanceInTransactionTable;
    Logger log = Logger.getLogger("ViewCheckingAccountsPage");

    JavascriptExecutor js = (JavascriptExecutor) driver;


    public void userClicksOnViewCheckingOption() {
        viewCheckingOption.click();
    }

    public void verifyViewCheckingAccountsPageTitle() {
        Assertions.assertTrue(pageTitle.isDisplayed());
    }

    public void userScrollsDownToTransactionsTable() {
        js.executeScript("arguments[0].scrollIntoView();", transactionsTable);
        Assertions.assertTrue(transactionsTable.isDisplayed());
    }

    public void userCanSeeTransactionDetails() {
        Assertions.assertTrue(transactionDetails.isDisplayed());
        System.out.println(transactionDetails.getText());
    }

    public void userFindsAccountAndActivatesToggleBtn(String accountName) {
        for (int i = 0; i < listOfCheckingAccounts.size(); i++) {
            if (listOfCheckingAccounts.get(i).getText().contains(accountName)) {
                System.out.println("Account name: " + accountName);
                js.executeScript("arguments[0].scrollIntoView();", listOfCheckingAccounts.get(i));
                listOfCheckingAccountsToggleBtn.get(i).click();
            }
        }
    }

    public void userVerifiesWithdrawAmountAndNewBalance(String amount) {
        String msg = "Withdraw amount on table is not matching with actual withdraw amount";
        String expected = withdrawAmountOnTable.getText();
        String actual = "$-" + amount;
        Assertions.assertEquals(msg, expected, actual);
    }

    public void userVerifiesNewBalanceInTransactionTable() {
        log.info(newBalanceInTransactionTable.getText());
    }

    public void userVerifiesTransferAmountInTransactionTable(String transferAmount, String balanceAfterTransfer) {
        String msg = "Transfer amount on table is not matching with actual transfer amount";

        String actualAmount = withdrawAmountOnTable.getText();
        String actualBalance = newBalanceInTransactionTable.getText();

        String expectedAmount = "$" + transferAmount;
        String expectedBalance = "$" + balanceAfterTransfer;
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedAmount.trim(), actualAmount.trim(), msg),
                () -> Assertions.assertEquals(expectedBalance.trim(), actualBalance.trim(), msg)
        );

        log.info("Transfer amount: " + newBalanceInTransactionTable.getText());
    }

    public void verifyAccountWidgetAfterTransaction(String accountName, String balanceAfterTransfer) {
        String xlocator = "//div[contains(text(), '" + accountName.trim() + "')]/following-sibling::div[contains(text(), 'Balance')]";
        WebElement accountWidget = driver.findElement(By.xpath(xlocator));
        Assertions.assertTrue(accountWidget.getText().contains(balanceAfterTransfer), "Account widget doesn't contain correct after transfer balance");
    }
}
