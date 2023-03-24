package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.InternalTransferPage;
import pages.ViewCheckingAccountsPage;

public class TransferBetweenAccountsSteps {

    HomePage homePage = new HomePage();
    InternalTransferPage internalTransferPage = new InternalTransferPage();
    ViewCheckingAccountsPage checkingAccountsPage = new ViewCheckingAccountsPage();
    @Given("user clicks on Transfer Between Accounts option")
    public void userClicksOnTransferBetweenAccountsOption() {
        homePage.userClicksOnTransferBetweenAccountsOption();
    }

    @And("user is redirected to Internal Transfer page")
    public void userIsRedirectedToInternalTransferPage() {
        internalTransferPage.verifyInternalTransferPage();
    }

    @And("user chooses {string} and {string}")
    public void userChoosesAnd(String fromAccount, String toAccount) {
        internalTransferPage.userChoosesFromAccountAndToAccount(fromAccount,toAccount);
    }

    @And("user enters transfer {string}")
    public void userEntersTransfer(String transferAmount) {
        internalTransferPage.userEntersTransferAmount(transferAmount);
    }

    @When("user clicks on submit button on Internal Transfer page")
    public void userClicksOnSubmitButtonOnInternalTransferPage() {
        internalTransferPage.userClicksSubmitBtn();
    }

    // Fixed
    @Then("user verifies {string} and {string} in transaction table")
    public void userVerifiesTransferAmountInTransactionTable(String transferAmount, String balanceAfterTransfer) {
        checkingAccountsPage.userVerifiesTransferAmountInTransactionTable(transferAmount, balanceAfterTransfer);
    }

    @And("user verifies that amount is less than available balance")
    public void userVerifiesThatAmountIsLessThanAvailableBalance() {
        internalTransferPage.verifyTransferAmountIsLessThanAvailableBalance();
    }


    // new steps
    @When("user select from {string}")
    public void selectFromAccount(String fromAccountName) {
        internalTransferPage.selectFromAccount(fromAccountName);
    }

    @When("user select to {string}")
    public void selectToAccount(String toAccountName) {
        internalTransferPage.selectToAccount(toAccountName);
    }

    @Then("verify that {string} balance is {string}")
    public void verifyAccountWidgetAfterTransaction(String accountName, String balanceAfterTransfer) {
        checkingAccountsPage.verifyAccountWidgetAfterTransaction(accountName, balanceAfterTransfer);
    }
}
