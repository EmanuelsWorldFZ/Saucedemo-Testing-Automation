package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage {
  private final Page page;

  public CartPage(Page page) {
    this.page = page;
  }

  public int getItemCount() {
    return page.locator(".cart_item").count();
  }

  public void removeItem(String productName) {
    page.locator(".cart_item")
        .filter(new Locator.FilterOptions().setHasText(productName))
        .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
        .click();
  }

  public void checkout() {
    page.getByTestId("checkout").click();
  }
}
