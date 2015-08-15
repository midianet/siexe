package conceito.teste.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class AlterarCliente {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {

    FirefoxProfile newProfile = new FirefoxProfile();
    newProfile.setPreference("browser.cache.disk.enable", false);
    newProfile.setPreference("browser.cache.memory.enable", false);
    newProfile.setPreference("browser.cache.offline.enable", false);
    driver = new FirefoxDriver(newProfile);

    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAlterarCliente() throws Exception {
    driver.get(baseUrl + "/siexe/pessoa.xhtml");
    driver.findElement(By.id("formulario:txNome")).clear();
    driver.findElement(By.id("formulario:txNome")).sendKeys("Cliente Feliz");
    driver.findElement(By.id("formulario:j_idt14")).click();
    driver.findElement(By.cssSelector("img")).click();
    driver.findElement(By.id("formulario:txNome")).clear();
    driver.findElement(By.id("formulario:txNome")).sendKeys("Cliente Feliz Alterado");
    driver.findElement(By.id("formulario:j_idt12")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
