package test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author andreendo
 */
public class ActivityDiaryTest {
    
    //Apk file within the project
    private static String APKFILELOCATION = "./res/de.rampro.activitydiary_122.apk";
    protected AndroidDriver d;

    @Before
    public void before() throws MalformedURLException {
        File apkFile = new File(APKFILELOCATION);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus4-22");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());

        d = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void after() {
        if (d != null) {
            d.quit();
        }
    }

    @Test
    public void test01() {
        d.findElementByAccessibilityId("Add Activity").click();
        
        d.findElement(By.id("de.rampro.activitydiary:id/edit_activity_name"))
                .sendKeys("atividade 1");
        
        d.findElementByAccessibilityId("Save").click();
        
        
        assertEquals("atividade 1", d.findElement(By.id("de.rampro.activitydiary:id/activity_name")).getText()); 
    }
}
