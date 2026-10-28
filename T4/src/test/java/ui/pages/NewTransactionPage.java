package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NewTransactionPage {

    private Page page;
    private Locator firstUser;
    private Locator amountInput;
    private Locator noteInput;
    private Locator payButton;

    public void init(Page page) {
        this.page = page;
        firstUser = page.getByText(
                "Kristian Bradtke",
                new Page.GetByTextOptions().setExact(true));
        amountInput = page.getByPlaceholder("Amount", new Page.GetByPlaceholderOptions().setExact(true));
        noteInput = page.getByPlaceholder("Add a note", new Page.GetByPlaceholderOptions().setExact(true));
        payButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Pay"));
    }

    public void selectFirstAvailableUser() {
        firstUser.click();
    }

    public void pay(String amount, String note) {
        amountInput.fill(amount);
        noteInput.fill(note);
        payButton.click();
    }
}
