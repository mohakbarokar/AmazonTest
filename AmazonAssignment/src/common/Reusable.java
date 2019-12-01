package common;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import setupconfig.BaseTestScript;

public class Reusable {

    Logger logger = new Logger();

    /**
     * Verifies Web page title with expected page title
     * 
     * @param expectedTitle
     * @param actualTitle
     * @return true if title matches expected title else false
     */
    public boolean verifyTitle(String expectedTitle) {
	boolean titleMatched = false;
	logger.info("Getting Title of Web Page");
	String actualTitle = BaseTestScript.driver.getTitle();
	logger.info("Title of Page is " + actualTitle);
	if (actualTitle.equalsIgnoreCase(expectedTitle)) {
	    titleMatched = true;
	}
	return titleMatched;
    }

    /**
     * 
     * @param element
     * @throws InterruptedException
     */
    public void clickOnElement(UIElement element) throws InterruptedException {
	Thread.sleep(2000);
	try {
	    logger.info("Enter in click method:" + element.getCaption());
	    BaseTestScript.driver.findElement(element.getByElement()).click();

	} catch (Exception e) {
	    logger.info("Element " + element.getCaption() + " not found.\n Original Error: " + e);
	    Assert.assertTrue(false, "Element " + element.getCaption() + " not found.\n Original Error: " + e);
	}
    }

    public boolean ClickOnIndex(UIElement element, int index) throws InterruptedException {
	List<WebElement> ElementList = BaseTestScript.driver.findElements(element.getByElement());
	try {

	    ElementList.get(index).click();

	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	return true;

    }

    /* Method used for getting the Element List */
    public List<WebElement> GetElementsList(UIElement element) {

	List<WebElement> ElementsList = BaseTestScript.driver.findElements(element.getByElement());
	return ElementsList;
    }

    public boolean isElementPresent(UIElement element) {

	try {

	    WebDriverWait wait = new WebDriverWait(BaseTestScript.driver, 2);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(element.getByElement()));
	    return true;

	} catch (Exception e) {
	    logger.info("Failed in check visibility method.");
	}
	return false;
    }

    public boolean retryingFindClick(UIElement element) {
	boolean result = false;
	int attempts = 0;
	while (attempts < 2) {
	    try {
		logger.info("Enter in click method:" + element.getCaption());
		BaseTestScript.driver.findElement(element.getByElement()).click();
		result = true;
		break;
	    } catch (StaleElementReferenceException e) {
	    }
	    attempts++;
	}
	return result;
    }

    public void scrollBottomOfPage() {

	((JavascriptExecutor) BaseTestScript.driver).executeScript("javascript:window.scrollBy(0,800)");
    }

    /* This method is used to enter given text on edit text box */
    public void sendKey(UIElement element, String textToSend) {

	try {
	    BaseTestScript.driver.findElement(element.getByElement()).clear();
	    BaseTestScript.driver.findElement(element.getByElement()).sendKeys(textToSend);

	} catch (Exception e) {
	    logger.info("Element " + element.getCaption() + " not found.\n Original Error: " + e);
	    Assert.assertTrue(false, "Element " + element.getCaption() + " not found.\n Original Error: " + e);
	}
    }

    public void Wait_for_Element(UIElement element) throws InterruptedException {
	logger.info("Enter in wait method:" + element.getCaption());

	try {
	    WebDriverWait wait = new WebDriverWait(BaseTestScript.driver, Constants.lngTIMEOUT);
	    wait.until(ExpectedConditions.presenceOfElementLocated(element.getByElement()));
	}

	catch (Exception e) {
	    logger.info("Wait timeout for element " + element.getCaption());
	}

    }

    public void Wait_for_Element(UIElement element, long timeout) {
	logger.info("Enter in wait method:" + element.getCaption());

	try {
	    WebDriverWait wait = new WebDriverWait(BaseTestScript.driver, timeout);
	    wait.until(ExpectedConditions.presenceOfElementLocated(element.getByElement()));
	} catch (Exception e) {

	}

    }

    public boolean isSorted(List<WebElement> elementList) {
	boolean isPriceSorted = false;
	for (int i = 0; i < 10; i++) {
	    int firstValueToCompare = Integer.parseInt(elementList.get(i).getText());
	    logger.info("Current first Value to compare " + firstValueToCompare);
	    int secondValueToCompare = Integer.parseInt(elementList.get(i + 1).getText());
	    logger.info("Current second Value to compare " + secondValueToCompare);
	    if (firstValueToCompare <= secondValueToCompare) {
		logger.info("Price of Item " + i + " is less than price of item at " + (i + 1));
		isPriceSorted = true;
	    } else {
		isPriceSorted = false;
		break;
	    }
	}
	return isPriceSorted;
    }

    public String getText(UIElement element) {
	logger.info("Enter in click method:" + element.getCaption());
	String textPresent = BaseTestScript.driver.findElement(element.getByElement()).getText();
	return textPresent;
    }

    public List<String> getTextList(UIElement element) {
	List<String> textList = new ArrayList<>();
	List<WebElement> elementList = GetElementsList(element);
	for (int i = 0; i < elementList.size(); i++) {
	    textList.add(elementList.get(i).getText());
	}
	logger.info("List of Text: " + textList);
	return textList;
    }

}
