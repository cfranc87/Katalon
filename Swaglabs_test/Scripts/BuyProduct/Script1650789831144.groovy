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
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement


'go to checkout'
WebUI.click(findTestObject('Object Repository/homePage/cart_button'))
WebUI.waitForElementPresent(findTestObject('Object Repository/CartPage/checkout_button'), GlobalVariable.timeout_breve)
WebUI.click(findTestObject('Object Repository/CartPage/checkout_button'))

'open page and fill one field at a time'
WebUI.waitForElementPresent(findTestObject('Object Repository/CheckoutInfoPage/pageTitle'), GlobalVariable.timeout_breve)
WebUI.setText(findTestObject('Object Repository/CheckoutInfoPage/firstName'), firstname)
WebUI.click(findTestObject('Object Repository/CheckoutInfoPage/continue_button'))
WebUI.verifyElementPresent(findTestObject('Object Repository/CheckoutInfoPage/errorMessage'), GlobalVariable.timeout_breve)
WebUI.setText(findTestObject('Object Repository/CheckoutInfoPage/lastName'), lastname)
WebUI.click(findTestObject('Object Repository/CheckoutInfoPage/continue_button'))
WebUI.verifyElementPresent(findTestObject('Object Repository/CheckoutInfoPage/errorMessage'), GlobalVariable.timeout_breve)
WebUI.setText(findTestObject('Object Repository/CheckoutInfoPage/postalcode'), postalcode)
WebUI.click(findTestObject('Object Repository/CheckoutInfoPage/continue_button'))
WebUI.verifyElementNotPresent(findTestObject('Object Repository/CheckoutInfoPage/errorMessage'), GlobalVariable.timeout_breve)

'open page and verify cart list'
WebDriver driver = DriverFactory.getWebDriver()
WebElement Prodotti = driver.findElement(By.xpath('//div[@class="cart_item"]'))
List<WebElement> nProd = Prodotti.findElements(By.xpath('//div[@class="cart_item"]'))
nProdotti = nProd.size()
println(nProdotti)

assert nProdotti == GlobalVariable.nPurchase-1

//WebUI.verifyElementPresent(findTestObject('Object Repository/CheckoutInfoPage/errorMessage'), GlobalVariable.timeout_breve)

'verify order info'
assert WebUI.getText(findTestObject('Object Repository/CheckoutOverview/PaymantInfo')) == PaymentInfo : 'Paymant info error'
assert WebUI.getText(findTestObject('Object Repository/CheckoutOverview/ShippingInfo')) == ShippingInfo : 'ShippingInfo info error'

println(WebUI.getText(findTestObject('Object Repository/CheckoutOverview/totInfo')))
assert WebUI.getText(findTestObject('Object Repository/CheckoutOverview/totInfo')) == tot : 'Tot info error'


'confirm order, check complete and back to home'
WebUI.scrollToElement(findTestObject('Object Repository/CheckoutOverview/finish_button'),GlobalVariable.timeout_breve)
WebUI.click(findTestObject('Object Repository/CheckoutOverview/finish_button'))

WebUI.verifyElementPresent(findTestObject('Object Repository/CheckoutComplete/checkoutComplete'), GlobalVariable.timeout_breve)


WebUI.scrollToElement(findTestObject('Object Repository/CheckoutComplete/backHome_button'),GlobalVariable.timeout_breve)
WebUI.click(findTestObject('Object Repository/CheckoutComplete/backHome_button'))

