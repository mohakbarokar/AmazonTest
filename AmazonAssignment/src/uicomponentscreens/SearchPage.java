package uicomponentscreens;

import org.testng.Reporter;
import common.UIElement;
import common.UIElementType;
import setupconfig.BaseTestScript;

public class SearchPage {

    public UIElement resultCount = new UIElement(
	    "//div[@class='a-section a-spacing-small a-spacing-top-small']/span[1]", "Result Count",
	    UIElementType.byXpath);
    public UIElement primeOnlyCheckbox = new UIElement("//i[@aria-label='Prime Eligible']", "Only Prime",
	    UIElementType.byXpath);
    public UIElement primeSearchResult = new UIElement(
	    "//div[@class='s-result-list s-search-results sg-row']//following::div[starts-with(@data-asin,'B0')]",
	    "Prime Results", UIElementType.byXpath);
    public UIElement onlyPrimeContentVisible = new UIElement(
	    "//div[@class='s-result-list s-search-results sg-row']//following::div[starts-with(@data-asin,'B0')]//i[@class='a-icon a-icon-prime a-icon-medium']",
	    "Prime Icon Contents", UIElementType.byXpath);
    public UIElement sortFilter = new UIElement(
	    "//div[@class='a-section a-spacing-small a-spacing-top-small a-text-right']//span[@class='rush-component']",
	    "Sort Filter", UIElementType.byXpath); // Price: Low to High
    public UIElement priceLowToHigh = new UIElement("//a[@id='s-result-sort-select_1']",
	    "Filter Option for Low To High", UIElementType.byXpath);
    public UIElement itemName = new UIElement("//span[@class='a-size-medium a-color-base a-text-normal']", "Item names",
	    UIElementType.byXpath);
    public UIElement itemPrice = new UIElement(
	    "//div[@class='s-result-list s-search-results sg-row']//following::div[starts-with(@data-asin,'B0')]//span[@class='a-price-whole']",
	    "Item Price", UIElementType.byXpath);
    public UIElement firstSearchedItem = new UIElement(
	    "//span[@class='celwidget slot=SEARCH_RESULTS template=SEARCH_RESULTS widgetId=search-results index=0']//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']",
	    "First Searched Item", UIElementType.byXpath);
    public UIElement secondSearchedItem = new UIElement(
	    "//span[@class='celwidget slot=SEARCH_RESULTS template=SEARCH_RESULTS widgetId=search-results index=1']//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']",
	    "Second Searched Item", UIElementType.byXpath);

    public String getCount(UIElement element) {
	String resultString = BaseTestScript.driver.findElement(element.getByElement()).getText();
	String[] words = resultString.split(" ");
	System.out.println("Search results found : " + words[3]);
	Reporter.log("Search results found : " + words[3]);
	return words[3];
    }

}
