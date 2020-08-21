package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import data.JSONDataReader;
import data.JSONDataReader1;
import pages.HomePage;
import pages.UserRegistrationPage;

public class UserRegistrationTests extends TestBase{

	HomePage homeObj ;
	UserRegistrationPage UserRegisterObj;

	
	
	@Test
	public void UserCanRegisterSuccessfully() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{

		JSONDataReader jsonReader = new JSONDataReader();
		jsonReader.jsonReader();
		homeObj = new HomePage(driver);
		homeObj.OpenRegisterPage();

		UserRegisterObj = new UserRegistrationPage(driver);
		UserRegisterObj.UserCanRegister(jsonReader.firstname, jsonReader.lastname, jsonReader.email, jsonReader.password);
		//Assert.assertTrue(UserRegisterObj.successRegisterl.getText().contains("التسجيل اكتمل!"));
		//Assert.assertTrue(UserRegisterObj.successMessage.getText().contains("تمت عملية التسجيل بنجاح! يرجى مراجعة بريدك الإلكتروني لإتمام عملية التحقق من عنوان بريدك الإلكتروني."));
		
		System.out.println(UserRegisterObj.reCaptcha.isDisplayed());
		System.out.println(UserRegisterObj.reCaptcha.isEnabled());
		System.out.println("WE CAN'T AUTOMATE CAPTCHA , THEREFORE THIS TEST IS OUT OF SCOPE, SO WE CAN'T SIGNUP IN THIS CASE TILL THE DEV TEAM DISABLE THIS FEATURE FROM THE BACKEND");
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
	public void UserRegisterFailure() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		JSONDataReader1 jsonReader1 = new JSONDataReader1();
		jsonReader1.jsonReader1();
		homeObj = new HomePage(driver);
		homeObj.OpenRegisterPage();

		UserRegisterObj = new UserRegistrationPage(driver);
		UserRegisterObj.UserCannotRegister(jsonReader1.firstname, jsonReader1.lastname, jsonReader1.email, jsonReader1.password);
		Assert.assertTrue(UserRegisterObj.emailError.getText().contains("Please enter a valid email address."));
		//Assert.assertEquals(UserRegisterObj.passwordError, "يجب أن تتكون كلمة السر من 8 خانات على الأقل.");
		Assert.assertTrue(UserRegisterObj.passwordWeak.getText().contains("ضعيف جدا"));
		System.out.println(UserRegisterObj.reCaptcha.isDisplayed());
		System.out.println(UserRegisterObj.reCaptcha.isEnabled());
		System.out.println("WE CAN'T AUTOMATE CAPTCHA , THEREFORE THIS TEST IS OUT OF SCOPE");
		Thread.sleep(1000);
	}

	

	@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
	public void UserCanLoginSuccessfully() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		JSONDataReader jsonReader = new JSONDataReader();
		jsonReader.jsonReader();
		homeObj=new HomePage(driver);
		homeObj.OpenLoginPage();
		UserRegisterObj = new UserRegistrationPage(driver);
		Assert.assertTrue(UserRegisterObj.loginMessage.getText().contains("قم بتسجيل الدخول إلى GOcardi"));
		UserRegisterObj.UserLogin(jsonReader.email, jsonReader.password);
		System.out.println("WE CAN'T AUTOMATE CAPTCHA , THEREFORE THIS TEST IS OUT OF SCOPE SO WE CAN'T LOGIN IN THIS CASE TILL TGHE DEV TEAM DISABLE THIS FEATURE FROM GTHE BACKEND");
		Thread.sleep(3000);

	}


}
