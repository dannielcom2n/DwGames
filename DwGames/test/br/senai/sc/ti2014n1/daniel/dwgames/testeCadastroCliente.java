package br.senai.sc.ti2014n1.daniel.dwgames;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testeCadastroCliente {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testECadastroCliente() throws Exception {
		driver.get(baseUrl + "/DwGames/admin/clientelist.xhtml");
		driver.findElement(By.linkText("Cadastre-se")).click();
		driver.findElement(By.linkText("Menu")).click();
		driver.findElement(By.linkText("Listar Clientes")).click();
		assertEquals(
				"maira",
				driver.findElement(
						By.xpath("//form[@id='j_idt45']/table[2]/tbody/tr[14]/td[2]"))
						.getText());
		driver.findElement(By.name("j_idt45:j_idt50")).clear();
		driver.findElement(By.name("j_idt45:j_idt50")).sendKeys("maira");
		driver.findElement(By.name("j_idt45:j_idt53")).clear();
		driver.findElement(By.name("j_idt45:j_idt53")).sendKeys("3334455");
		driver.findElement(By.name("j_idt45:j_idt56")).clear();
		driver.findElement(By.name("j_idt45:j_idt56")).sendKeys("maira@mail");
		driver.findElement(By.name("j_idt45:j_idt59")).clear();
		driver.findElement(By.name("j_idt45:j_idt59")).sendKeys("maira");
		driver.findElement(By.name("j_idt45:j_idt62")).clear();
		driver.findElement(By.name("j_idt45:j_idt62")).sendKeys("feminino");
		driver.findElement(By.name("j_idt45:j_idt64")).click();
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
