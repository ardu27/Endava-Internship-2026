package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

    private Page page;

    // Locators
    private Locator logoutButton;
    private Locator transactionList;
    private Locator myAccountLink;
    private Locator mineTab;
    private Locator newTransactionButton;
    private Locator homeLink;

    public void init(Page page) {
        this.page = page;
        logoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout"));
        transactionList = page.locator("//div[@data-test='transaction-list']");
        myAccountLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Account"));
        mineTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Mine"));
        newTransactionButton = page.locator("a[href='/transaction/new']");
        homeLink = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Home"));
        transactionList.isVisible();
    }

    public boolean isLogoutButtonVisible() {
        return logoutButton.isVisible();
    }

    public boolean isHomePageVisible() {
        return transactionList.isVisible();
    }

    public MyAccountPage navigateToMyAccount(){
        myAccountLink.click();
        MyAccountPage myAccountPage = new MyAccountPage();
        myAccountPage.init(page);
        return myAccountPage;
    }

    public void selectMineTab() {
        mineTab.click();
    }

    public NewTransactionPage startNewTransaction() {
        newTransactionButton.click();
        NewTransactionPage transactionPage = new NewTransactionPage();
        transactionPage.init(page);
        return transactionPage;
    }

    public void navigateHome() {
        homeLink.click();
    }

    public TransactionDetailPage openTransaction(String note) {
        page.getByText(note, new Page.GetByTextOptions().setExact(true)).click();
        TransactionDetailPage detailPage = new TransactionDetailPage();
        detailPage.init(page);
        return detailPage;
    }

}
