package common;

import org.openqa.selenium.By;

/*Defines UIObject skeleton. All screen components will be created as an object of this class*/

public class UIElement {

    String element_Identifier;
    String element_caption;
    UIElementType element_Type;

    public UIElement(String id, String caption, UIElementType type) {
	this.element_Identifier = id;
	this.element_caption = caption;
	this.element_Type = type;
    }

    public void click() {

    }

    public By getByElement() {
	By byElement = null;
	switch (element_Type) {

	case byId:
	    byElement = By.id(element_Identifier);
	    break;
	case byName:
	    byElement = By.name(element_Identifier);
	    break;
	case byXpath:
	    byElement = By.xpath(element_Identifier);
	    break;
	case byClass:
	    byElement = By.className(element_Identifier);
	    break;
	case byTagName:
	    byElement = By.tagName(element_Identifier);
	    break;
	case byCssSelector:
	    byElement = By.cssSelector(element_Identifier);
	    break;
	case byLinkText:
	    byElement = By.linkText(element_Identifier);
	    break;
	default:
	    System.out.println("Invalid type of element");
	    break;
	}
	return byElement;

    }

    public String getCaption() {
	return element_caption;
    }

    public String getId() {

	return element_Identifier;

    }

}
