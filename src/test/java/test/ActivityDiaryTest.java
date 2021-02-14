package test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author andreendo
 */
public class ActivityDiaryTest {

    //Apk file within the project
    private static String APKFILELOCATION = "./res/app.apk";
    protected AndroidDriver d;

    @Before
    public void before() throws MalformedURLException {
        File apkFile = new File(APKFILELOCATION);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities
                .setCapability(
                        MobileCapabilityType.DEVICE_NAME, 
                        "Nexus_4_API_25");
        capabilities
                .setCapability(
                        MobileCapabilityType.PLATFORM_NAME, 
                        "Android");
        capabilities
                .setCapability(
                        MobileCapabilityType.APP, 
                        apkFile.getAbsolutePath());

        d = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        d.findElement(By.id("android:id/button1")).click();

        d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        if (d != null) {
            d.quit();
        }
    }

    

    @Test
    public void adicionarPessoaTest() {
        d.findElementByAccessibilityId("Add traveler to trip").click();
        d.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Add traveler to trip\"]")).click();
        d.findElement(By.id("Add traveler to trip")).click();
        
        
        d.findElement(By.id("de.koelle.christian.trickytripper:id/option_accept")).sendKeys("Lucas");
        
        assertEquals("Lucas", d.findElementById("android:id/text1").toString());

    }

}
