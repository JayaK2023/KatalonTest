import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser(GlobalVariable.url)

WebUI.maximizeWindow()

WebUI.click(findTestObject("Object Repository/Page_AirRadar/button_Consent"))

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_AirRadar/Page_Menu/webElement_About'),GlobalVariable.timeout,FailureHandling.STOP_ON_FAILURE)

List<WebElement> allMenuItems=WebUI.findWebElements(findTestObject('Object Repository/Page_AirRadar/Page_Menu/webElement_AllMenuItems'), GlobalVariable.timeout)
WebDriver driver=DriverFactory.getWebDriver()
for(WebElement e:allMenuItems)
{

	Actions a =new Actions(driver)
	
	a.moveToElement(e).perform()
	List<WebElement> allSubMenuItems=WebUI.findWebElements(findTestObject('Object Repository/Page_AirRadar/Page_Menu/webElement_SubMenuItems'), GlobalVariable.timeout)
	
	for(WebElement e1:allSubMenuItems)
	{
		e1.click();
		WebUI.waitForPageLoad(GlobalVariable.timeout)
	}
		
}