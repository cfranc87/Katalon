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


'add to cart n puchase and verify badge'

for(int i=1;i<=GlobalVariable.nPurchase;i++) {

WebUI.click(findTestObject('Object Repository/homePage/AddToCart_botton', ['index' : i]))
WebUI.waitForElementPresent(findTestObject('Object Repository/homePage/CartBadge', ['badge' : i]), GlobalVariable.timeout_breve)

}

'verify number of slected object in cart'
WebUI.click(findTestObject('Object Repository/homePage/cart_button'))

WebDriver driver = DriverFactory.getWebDriver()
WebElement Prodotti = driver.findElement(By.xpath('//div[@class="cart_item"]'))
List<WebElement> nProd = Prodotti.findElements(By.xpath('//div[@class="cart_item"]'))
nProdotti = nProd.size()
println(nProdotti)

assert nProdotti == GlobalVariable.nPurchase: 'number of products purchased not correct'


'cancel the first product and verify update cart'
WebUI.click(findTestObject('Object Repository/CartPage/Remove_button'))
WebUI.waitForElementPresent(findTestObject('Object Repository/homePage/CartBadge', ['badge' : GlobalVariable.nPurchase-1]), GlobalVariable.timeout_breve)

WebElement ProdottiN = driver.findElement(By.xpath('//div[@class="cart_item"]'))
List<WebElement> nProdN = ProdottiN.findElements(By.xpath('//div[@class="cart_item"]'))
nProdottiN = nProdN.size()
println(nProdottiN)

assert nProdottiN == GlobalVariable.nPurchase-1: 'number of products purchased not correct'


'go bach to home'
WebUI.click(findTestObject('Object Repository/CartPage/ContinueShopping_button'))


