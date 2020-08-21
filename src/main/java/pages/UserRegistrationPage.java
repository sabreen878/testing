package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase{

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}


	@FindBy(id = "firstname")
	WebElement firstNameTxtBox;
	
	@FindBy(id = "lastname")
	WebElement lastNameTxtBox;
	
	@FindBy(id = "email")
	WebElement EmailTxtBox;
	
	@FindBy(id = "password")
	WebElement passwordTxtBox;
	
	@FindBy(css = ".input-group-append")
	WebElement showIcon;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/form/div[5]/div/label")
	WebElement termsAndConditions;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/form/div[6]/div/label")
	WebElement offers;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/form/div[7]/div/div/div/div/iframe")
	public WebElement reCaptcha;

	@FindBy(className = "div.recaptcha-checkbox-checkmark")
	public WebElement recaptCheckMark;
	
	@FindBy(className = "button.btn.btn-primary.btn-block.register")
	public WebElement register;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div[1]")
	public WebElement successRegisterl;
	
	@FindBy(className = "div.row.text-center.col-12.col-lg-6.mx-auto")
	public WebElement successMessage;
	
	@FindBy(id = "email-error")
	public WebElement emailError;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/form/div[4]/div[2]/div[2]/span")
	public WebElement passwordWeak;
	
	@FindBy(id = "password-error")
	public WebElement passwordError;
	
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/form/div[2]")
	WebElement nextBTN;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/form/div[3]/label")
	WebElement rememberMeCheckBox;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[1]/div[1]")
	public WebElement loginMessage;
	
	@FindBy(id = "login2Btn")
	WebElement loginBTN;
	
	public void UserCanRegister(String firstName, String lastName, String email, String password) throws InterruptedException
	
	{
		setTextElementText(firstNameTxtBox, firstName);
		setTextElementText(lastNameTxtBox, lastName);
		setTextElementText(EmailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		clickButton(showIcon);
		clickButton(termsAndConditions);
		clickButton(offers);
		scrollToBottom();
		
		/* WE CAN'T AUTOMATE CAPTCHA , THEREFORE THIS TEST IS OUT OF SCOPE */
//		driver.switchTo().frame(reCaptcha);
//		clickButton(recaptCheckMark);
		//clickButton(register);
	}
	
	
	
	public void UserCannotRegister(String firstName, String lastName, String email, String password) throws InterruptedException
	
	{
		setTextElementText(firstNameTxtBox, firstName);
		setTextElementText(lastNameTxtBox, lastName);
		setTextElementText(EmailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		clickButton(showIcon);
		clickButton(termsAndConditions);
		clickButton(offers);
		scrollToBottom();
	}
	
	
	public void UserLogin(String email, String password)
	{
	setTextElementText(EmailTxtBox, email);	
	clickButton(nextBTN);
	setTextElementText(passwordTxtBox, password);
	clickButton(showIcon);
	clickButton(rememberMeCheckBox);
	//clickButton(loginBTN);
	}
}
