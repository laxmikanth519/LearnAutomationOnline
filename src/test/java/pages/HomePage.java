package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage
{
WebDriver driver;
public HomePage(WebDriver Idriver)
{
	this.driver=Idriver;
}
@FindBy(xpath="//a[text()='signIn']") WebElement signInLink;
@FindBy(xpath="//a[text()='myAccount']") WebElement myAccountLink;
@FindBy(xpath="//a[text()='myCart']") WebElement myCartLink;
@FindBy(xpath="//a[text()='checkOut']")WebElement checkOutLink;
public void clickOnSignLink()
{
	signInLink.click();
}
public void clickOnMyAccountLink()
{
	myAccountLink.click();
}
public void clickOnMyCartLink()
{
	myCartLink.click();
}
public void clickOnCheckOutLink()
{
	checkOutLink.click();
}
public String getApplicationTitle()
{
	return driver.getTitle();
}
}