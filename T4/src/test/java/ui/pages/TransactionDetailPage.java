package ui.pages;

import com.microsoft.playwright.Page;

public class TransactionDetailPage {

    private Page page;

    public void init(Page page) {
        this.page = page;
    }

    public boolean containsTransaction(String note) {
        return page.getByText(note, new Page.GetByTextOptions().setExact(true)).isVisible();
    }
}
