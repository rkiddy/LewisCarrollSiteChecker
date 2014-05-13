
package org.ganymede.lcsna;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LCTest {

    private static final int tick = Integer.valueOf(System.getProperty("tick", "1"));

    public static void main(String arg[]) {

        String url = "http://lewiscarroll.org";

        boolean canQuit = true;

        System.out.println("\nUrl: \""+url+"\"");

        FirefoxProfile profile = new FirefoxProfile();
        //
        // I can customize things in the profile or use a specific profile directory here.
        //
        WebDriver driver = new FirefoxDriver(profile);

        driver.get(url);

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Lewis Carroll Society of North America");
            }
        });

        System.out.println("Page title: \"" + driver.getTitle() + "\"\n");

        Sleeper.sleepTightInSeconds(tick);

        try {

            WebDriverWait wait = new WebDriverWait(driver, 10);

            for (WebElement elt : driver.findElements(By.tagName("li"))) {
                String tagClass = elt.getAttribute("class");
                if (tagClass.startsWith("page_item")) {
                    System.out.println("found element with class: \""+tagClass+"\" and text: \""+elt.getText()+"\"");
                }
            }
/*
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("str")));
            WebElement link = driver.findElement(By.className("str"));
            link.click();

*/
            // --------------------------------

            System.out.println("Ok\n");

        } catch (Throwable t) { System.err.println("ERROR: "+t.getMessage()+"\n"); canQuit = false; }

        driver.quit();
    }
}
