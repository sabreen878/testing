package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}


	@FindBy(linkText = "تسجيل")
	WebElement registerLink;
	
	@FindBy(linkText = "دخول")
	WebElement loginLink;
	
	
	public void OpenRegisterPage()
	{
	clickButton(registerLink);	
	}
	
	
	public void OpenLoginPage()
	{
	clickButton(loginLink);	
	}
}
