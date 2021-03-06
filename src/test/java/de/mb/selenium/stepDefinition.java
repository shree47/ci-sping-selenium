package de.mb.selenium;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class stepDefinition {

	private WebDriver driver;

	@Given("^The selenium setup is complete$")
	public void setUp() throws MalformedURLException {
		String serverUrl = System.getProperty("grid.server.url");
		String gridServerUrl = "http://10.144.2.237:4444/wd/hub";
		if (serverUrl != null) {
			gridServerUrl = serverUrl;
		}
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		URL gridUrl = new URL(gridServerUrl);
		driver = new RemoteWebDriver(gridUrl, capability);
		driver.get("http://10.144.2.237:10000");
		
	}

	@When("^print a simple message$")
	public void printSimpleMessage() {
		System.out.println("Setup is complete!");
	}

	@Then("^aos application body is loaded and not null$")
	public void pageBodyIsNotNull() throws MalformedURLException {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue(bodyText != null);
	}

	@And("^close the web browser$")
	public void tearDownWebDriver() {
		driver.quit();
	}

}
