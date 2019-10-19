package com.otus.javaqa;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TestStep {
    private WebDriver driver;

    public TestStep(WebDriverType wdType, MutableCapabilities caps) {
        this.driver = WebDriverFactory.createDriver(wdType, caps);
        driver.manage().window().maximize();
    }

    @Step("Open Otus Homepage")
    public void openOtusHomepage() {
        driver.get(Constants.OTUS_HOMEPAGE);
    }

    @Step("Get Title of the current page")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Open Otus Operations Courses Page")
    public void openOperationsCoursesPage() {
        driver.get(Constants.OTUS_OPERATIONS_COURSES_PAGE);
    }

    @Step("Determine if certain course is present on the page")
    public boolean courseIsPresent(String courseName) {
        List<WebElement> courses = driver.findElements(By.xpath("//div[contains(@class, 'lesson-title')]"));
        for (WebElement we : courses) {
            if (we.getAttribute("innerText").trim().equals(courseName)) {
                Actions action = new Actions(driver);
                action.moveToElement(we).perform();
                takeScreenshot();
                return true;
            }
        }
        return false;
    }

    @Step
    @Attachment
    private byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Quit WebDriver")
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
