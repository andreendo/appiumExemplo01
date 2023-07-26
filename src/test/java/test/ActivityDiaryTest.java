package test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class ActivityDiaryTest {

	// Apk file within the project
	private static String APKFILELOCATION = "/home/andre/git/appiumExemplo01/res/de.rampro.activitydiary_122.apk";
	protected AndroidDriver d;
	protected WebDriverWait wait;

	@Before
	public void before() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName("Android")
				.setApp(APKFILELOCATION);

		d = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		wait = new WebDriverWait(d, Duration.ofSeconds(5));
	}

	@After
	public void after() {
		if (d != null) {
			d.quit();
		}
	}

	@Test
	public void test01() {
		d.findElement(AppiumBy.accessibilityId("Add Activity")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("de.rampro.activitydiary:id/edit_activity_name")));
		                     
		d.findElement(By.id("de.rampro.activitydiary:id/edit_activity_name")).sendKeys("atividade 1");

		d.findElement(AppiumBy.accessibilityId("Save")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("de.rampro.activitydiary:id/activity_name")));
		assertEquals("atividade 1", d.findElement(By.id("de.rampro.activitydiary:id/activity_name")).getText());
	}
}
