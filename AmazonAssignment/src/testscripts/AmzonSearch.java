package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common.Constants;
import common.Logger;
import common.Reusable;
import setupconfig.BaseTestScript;
import uicomponentscreens.AmazonCart;
import uicomponentscreens.AmazonHome;
import uicomponentscreens.SearchPage;

/**
 * Testscripts for Amazon Web App
 * 
 * @author Mohak Barokar
 *
 */

public class AmzonSearch extends BaseTestScript {

    Reusable reusable = new Reusable();
    AmazonHome amazonHome = new AmazonHome();
    AmazonCart amazonCart = new AmazonCart();
    SearchPage searchPage = new SearchPage();
    Logger logger = new Logger();

    /*
     * 1) Go to the url: https://www.amazon.in/ 2) On the home page next to search
     * box there is ‘All’ button, click on that and print the categories available
     * in the log
     */

    @Test(priority = 1)
    public void verifyShoppingCategory() throws InterruptedException {
	logger.info("Waiting for Web page to load");
	reusable.Wait_for_Element(amazonHome.amazonLogo);
	assertTrue(reusable.verifyTitle(Constants.strExpectedTitle));
	reusable.clickOnElement(amazonHome.categoryDropdown);
	reusable.Wait_for_Element(amazonHome.dropDownList);

	List<WebElement> categoryList = reusable.GetElementsList(amazonHome.dropDownList);

	logger.info("Search Results :: Count:: " + categoryList.size());
	logger.info("=========================================================");
	logger.info("Search Results :: Count:: " + categoryList.size());
	logger.info("=========================================================");
	logger.info("Categories Found:");

	for (int i = 0; i < categoryList.size(); i++) {
	    String getCategoryName = categoryList.get(i).getText();
	    logger.info((i + 1) + " " + getCategoryName);
	}

    }

    /*
     * 3) Search for “usb hub 3.0”and print the total number of products listed for
     * that search. 4) Add filter for ‘Prime’ and check if only ‘Prime’ enabled
     * items are listed 5) Disable ‘Prime’ and see if list is changed
     */

    @Test(priority = 2)
    public void verifySearch() throws InterruptedException {

	assertTrue(reusable.isElementPresent(amazonHome.searchBox));
	logger.info("Searching in Search Box");
	reusable.clickOnElement(amazonHome.searchBox);
	reusable.sendKey(amazonHome.searchBox, Constants.strSearchText);
	logger.info("Clicking on Search button");
	reusable.clickOnElement(amazonHome.searchBtn);
	logger.info("Getting Search results count");
	searchPage.getCount(searchPage.resultCount);
	logger.info("Clicking on Prime only checkbox");
	reusable.clickOnElement(searchPage.primeOnlyCheckbox);
	reusable.Wait_for_Element(searchPage.primeSearchResult);

	logger.info("Getting total results returned on page for prime");
	List<WebElement> primeFilteredResults = reusable.GetElementsList(searchPage.primeSearchResult);
	logger.info("=========================================================");
	logger.info("Total Results Filtered :: Count:: " + primeFilteredResults.size());
	logger.info("=========================================================");

	logger.info("Getting total results which are only prime on page");
	List<WebElement> resultsWithPrimeIcon = reusable.GetElementsList(searchPage.onlyPrimeContentVisible);
	logger.info("=========================================================");
	logger.info("Total Content with Prime Icon :: Count:: " + resultsWithPrimeIcon.size());
	logger.info("=========================================================");

	logger.info("Verifying if all results returned are prime");
	assertEquals(primeFilteredResults.size(), resultsWithPrimeIcon.size());

	logger.info("Clicking on Prime Checkbox");
	reusable.clickOnElement(searchPage.primeOnlyCheckbox);
	reusable.Wait_for_Element(searchPage.primeSearchResult);

	logger.info("Getting results size on page after unchecking prime");
	List<WebElement> primeUncheckedResult = reusable.GetElementsList(searchPage.primeSearchResult);
	logger.info("=========================================================");
	logger.info("Total Results Filtered :: Count:: " + primeUncheckedResult.size());
	logger.info("=========================================================");

	logger.info("Verifying if search results list is changed");
	assertNotEquals(primeUncheckedResult.size(), primeFilteredResults.size());
    }

    /*
     * 6) Sort by ‘Low to High’ and see if the list of items are sorted accordingly
     * in that page. i.e check if the items are listed according to the ascending
     * order of the price. [print :first 5-10 products name with price in ascending
     * order, as they appear]
     */

    @Test(priority = 3)
    public void verifySortLowToHighPrice() throws InterruptedException {
	reusable.Wait_for_Element(searchPage.sortFilter);
	reusable.clickOnElement(searchPage.sortFilter);
	reusable.clickOnElement(searchPage.priceLowToHigh);

	logger.info("Getting Result Names and prices after Sorting");
	List<WebElement> sortedSearchResults = reusable.GetElementsList(searchPage.itemName);
	List<WebElement> sortedPricesResults = reusable.GetElementsList(searchPage.itemPrice);

	logger.info("=========================================================");
	for (int i = 0; i < 10; i++) {
	    logger.info("Item : " + (i + 1) + " :: " + sortedSearchResults.get(i).getText() + " Price : Rs. "
		    + sortedPricesResults.get(i).getText());
	}
	logger.info("=========================================================");

	logger.info("Verifying if Items are listed in Ascending Price Order");
	assertTrue(reusable.isSorted(sortedPricesResults));
    }

    /*
     * 7) Select any two items and add to cart. Verify the count in cart is
     * reflecting properly and check if the two items that were selected is present
     * in the cart.
     */
    
    @Test(priority = 4)
    public void verifyAddToCart() throws InterruptedException {

	reusable.Wait_for_Element(searchPage.itemName);
	String firstItemName = reusable.getText(searchPage.firstSearchedItem);
	logger.info("First Item: " + firstItemName);
	String secondItemName = reusable.getText(searchPage.secondSearchedItem);
	logger.info("Second Item: " + secondItemName);

	logger.info("Clicking on " + firstItemName);
	reusable.ClickOnIndex(searchPage.itemName, 0);
	logger.info("Getting all the tabs opened after clicking on Item");
	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	logger.info("No Of tabs::: " + tabs.size());
	logger.info("Switching to Item Detail tab of " + firstItemName);
	driver.switchTo().window(tabs.get(1));
	logger.info("Adding Item to Cart");
	reusable.clickOnElement(amazonCart.addToCart);
	logger.info("Closing tab of " + firstItemName);
	driver.close();
	logger.info("Switching to Search results tab");
	driver.switchTo().window(tabs.get(0));
	reusable.ClickOnIndex(searchPage.itemName, 1);
	logger.info("Clicking on  " + secondItemName);
	reusable.clickOnElement(searchPage.secondSearchedItem);

	logger.info("Getting new opened tab");
	ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
	int tabsPresent= newtabs.size();
	System.out.println("No Of tabs::: " +tabsPresent );

	logger.info("Switching to Item Detail tab of " + secondItemName);
	driver.switchTo().window(newtabs.get(tabsPresent-1));
	reusable.Wait_for_Element(amazonCart.addToCart);
	reusable.clickOnElement(amazonCart.addToCart);
	Thread.sleep(10000);
	int itemsInCart = Integer.parseInt(reusable.getText(amazonCart.cartCount));
	logger.info("Items in cart= " + itemsInCart);
	logger.info("Verifying number of items reflected in cart");
	assertEquals(itemsInCart, Constants.itemsExpectedInCart);

	logger.info("Navigating to Cart");
	reusable.clickOnElement(amazonCart.cartButton);

	logger.info("Getting names of all items in Cart");
	List<String> itemNames = reusable.getTextList(amazonCart.itemsInCart);
	logger.info("Verifying if " + firstItemName + " is presnt");
	assertTrue(itemNames.contains(firstItemName));
	logger.info("Verifying if " + secondItemName + " is presnt");
	assertTrue(itemNames.contains(secondItemName));
    }
}
