package uicomponentscreens;

import common.UIElement;
import common.UIElementType;

public class AmazonCart {

    public UIElement addToCart = new UIElement("add-to-cart-button", "Add to Cart", UIElementType.byId);
    public UIElement cartCount = new UIElement("nav-cart-count", "No. of Items in Cart", UIElementType.byId);
    public UIElement cartButton = new UIElement("nav-cart", "Cart button", UIElementType.byId);
    public UIElement itemsInCart = new UIElement("//span[@class='a-size-medium sc-product-title a-text-bold']",
	    "Items available in cart", UIElementType.byXpath);
}
