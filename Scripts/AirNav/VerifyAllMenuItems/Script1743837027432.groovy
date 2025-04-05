import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.url)
WebUI.maximizeWindow()


WebUI.click(findTestObject("Object Repository/Page_AirRadar/button_Consent"))

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_AirRadar/Page_Menu/webElement_About'),GlobalVariable.timeout,FailureHandling.STOP_ON_FAILURE)


def getMenuItems() {
	return WebUI.findWebElements(findTestObject('Object Repository/Page_AirRadar/Page_Menu/webElement_AllMenuItems'), 10)
}

def getSubMenuItems(menuElement) {

	return menuElement.findElements(By.xpath('.//following-sibling::ul//li/a'))
	
	
}

// Verify menus and submenus dynamically
def menuElements = getMenuItems()

for(WebElement menuElement:menuElements)
{
	try {
		// Click the menu to reveal submenus
	

		// Wait for submenus to load
		WebUI.delay(2)

		def submenuElements = getSubMenuItems(menuElement)
		
		println submenuElements

		if (submenuElements.isEmpty()) {
			KeywordUtil.markPassed("No submenus for menuElement.getText()")
		} else {
			menuElement.click()
			for(WebElement submenuElement:submenuElements)
			{
				String submenuText = submenuElement.getText()
				submenuElement.click()

				// Verify URL
				String currentUrl = WebUI.getUrl()
				if (currentUrl.contains(submenuText.toLowerCase().replace(" ", ""))) {
					KeywordUtil.markPassed('Verified')
				} else {
					KeywordUtil.markFailed('Not matched')
				}

				// Go back to the main page
				WebUI.navigateToUrl(GlobalVariable.url)
				WebUI.delay(2)
			}
		}
	}
 
catch (Exception e) {
		e.printStackTrace()
	}
}

// Close browser
WebUI.closeBrowser()