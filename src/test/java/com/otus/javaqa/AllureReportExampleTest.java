package com.otus.javaqa;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

//
public class AllureReportExampleTest {
    private TestStep step;

    @BeforeEach
    public void setup() {
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--no-sandbox");
       options.addArguments("--disable-dev-shm-usage");
       options.addArguments("--headless");
       step = new TestStep(WebDriverType.CHROME, options);
    }

    @AfterEach
    public void teardown() {
        step.quit();
    }

    @Test
    @DisplayName("Otus Homepage Title Check")
    @Description("Checks that Otus Homepage Title is 'OTUS - Онлайн-образование'")
    @Severity(SeverityLevel.MINOR)
    public void homePageTitleTest() {
        step.openOtusHomepage();
        assertEquals("OTUS - Онлайн-образование", step.getPageTitle(), "Actual page title doesn't match the expected");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Java QA Engineer", "Python QA Engineer", "Mobile QA Engineer", "JavaScript QA Engineer"})
    @DisplayName("Java QA Course is Present on Operations Courses Page")
    @Description("Checks that given course is present on Operations Courses page")
    @Severity(SeverityLevel.CRITICAL)
    public void qaJavaCourseIsPresentTest(String course) {
        step.openOperationsCoursesPage();
        assertEquals(true, step.courseIsPresent(course), String.format("'%s' course is not present on the page", course));
    }
}