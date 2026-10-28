package ui.tests;

import framework.config.ConfigManager;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.NewTransactionPage;
import ui.pages.TransactionDetailPage;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionTest extends BaseTest {

    @Test
    void userCanCreateAndFindTransactionInMine() {
        LoginPage loginPage = new LoginPage();
        loginPage.init(page);
        loginPage.openLoginPage(ConfigManager.get("base.url"));

        HomePage homePage = loginPage.login(
                ConfigManager.get("login.username"),
                ConfigManager.get("login.password")
        );

        NewTransactionPage newTransactionPage = homePage.startNewTransaction();
        newTransactionPage.selectFirstAvailableUser();

        String amount = "12.50";
        String note = "Automation transaction " + UUID.randomUUID();
        newTransactionPage.pay(amount, note);

        homePage.navigateHome();
        homePage.selectMineTab();
        TransactionDetailPage detailPage = homePage.openTransaction(note);

        assertTrue(detailPage.containsTransaction(note),
                "The created transaction should be visible in its details");
    }
}
