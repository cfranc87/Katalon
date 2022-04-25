import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.io.File
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement



'verify empty cart'
WebUI.waitForElementNotPresent(findTestObject('Object Repository/homePage/CartBadge', ['badge' : 1]), GlobalVariable.timeout_breve)


'count of product'
WebDriver driver = DriverFactory.getWebDriver()
WebElement Prodotti = driver.findElement(By.xpath('//div[@class="inventory_item"]'))
List<WebElement> nProd = Prodotti.findElements(By.xpath('//div[@class="inventory_item"]'))
nProdotti = nProd.size()
println(nProdotti)

if(checkProductName) {
		'verify names of products'
		for (int i=1;i<=nProdotti;i++) {
			assert WebUI.getText(findTestObject('Object Repository/homePage/productName', ['index' : i])) == array_productsName[i-1] : 'product '+ array_productsName[i-1] +' not found'	
		}
}	

if(checkProductImage) {
	for (int j=1;j<=nProdotti;j++) {
		
		println(WebUI.getAttribute(findTestObject('Object Repository/homePage/image', ['index' : j]), 'src'))
		println(array_productsImage[j-1])
		assert WebUI.getAttribute(findTestObject('Object Repository/homePage/image', ['index' : j]), 'src') == array_productsImage[j-1] : 'image '+ array_productsImage[j-1] +' not found'
	}

	
if(checkProductOrder) {		
		'change order'
		WebUI.click(findTestObject('Object Repository/homePage/Order_button'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/homePage/order_za'), GlobalVariable.timeout_breve)
		WebUI.click(findTestObject('Object Repository/homePage/order_za'))
		
		
		'verify names of products'
		for (i=1;i<=nProdotti;i++) {
			assert WebUI.getText(findTestObject('Object Repository/homePage/productName', ['index' : i])) == array_productsNameZA[i-1] : 'product '+ array_productsNameZA[i-1] +' not found'
		}

}

	
}







