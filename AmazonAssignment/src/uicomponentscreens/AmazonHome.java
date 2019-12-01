package uicomponentscreens;

import common.UIElement;
import common.UIElementType;

public class AmazonHome {

    public UIElement amazonLogo = new UIElement("nav-logo", "Amazon Home Logo", UIElementType.byId);
    public UIElement categoryDropdown = new UIElement("nav-search-dropdown-card", "Category Dropdown",
	    UIElementType.byId);
    public UIElement dropDownList = new UIElement("//select[@id='searchDropdownBox']/option", "Categories",
	    UIElementType.byXpath);
    public UIElement searchBox = new UIElement("//input[@id='twotabsearchtextbox']", "Search Input Box",
	    UIElementType.byXpath);
    public UIElement searchBtn = new UIElement(
	    "//div[@class='nav-search-submit nav-sprite']//input[@class='nav-input']", "Search Button",
	    UIElementType.byXpath);
}
