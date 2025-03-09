import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest {

    public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    public static final String CHAPTER_3 = "Chapter 3. WebDriver Fundamentals";
    public static final String CHAPTER_4 = "Chapter 4. Browser-Agnostic Features";
    public static final String CHAPTER_5 = "Chapter 5. Browser-Specific Manipulation";
    public static final String CHAPTER_7 = "Chapter 7. The Page Object Model (POM)";
    public static final String CHAPTER_8 = "Chapter 8. Testing Framework Specifics";
    public static final String CHAPTER_9 = "Chapter 9. Third-Party Integrations";

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider()
    public Object[][] getData() {
        return new Object[][]{
                {CHAPTER_3, "Web form", "web-form.html", "Web form"},
                {CHAPTER_3, "Navigation", "navigation1.html", "Navigation example"},
                {CHAPTER_3, "Dropdown menu", "dropdown-menu.html", "Dropdown menu"},
                {CHAPTER_3, "Mouse over", "mouse-over.html", "Mouse over"},
                {CHAPTER_3, "Drag and drop", "drag-and-drop.html", "Drag and drop"},
                {CHAPTER_3, "Draw in canvas", "draw-in-canvas.html", "Drawing in canvas"},
                {CHAPTER_3, "Loading images", "loading-images.html", "Loading images"},
                {CHAPTER_3, "Slow calculator", "slow-calculator.html", "Slow calculator"},

                {CHAPTER_4, "Long page", "long-page.html", "This is a long page"},
                {CHAPTER_4, "Infinite scroll", "infinite-scroll.html", "Infinite scroll"},
                {CHAPTER_4, "Shadow DOM", "shadow-dom.html", "Shadow DOM"},
                {CHAPTER_4, "Cookies", "cookies.html", "Cookies"},
                {CHAPTER_4, "Frames", "frames.html", "Frames"},
                {CHAPTER_4, "IFrames", "iframes.html", "IFrame"},
                {CHAPTER_4, "Dialog boxes", "dialog-boxes.html", "Dialog boxes"},
                {CHAPTER_4, "Web storage", "web-storage.html", "Web storage"},

                {CHAPTER_5, "Geolocation", "geolocation.html", "Geolocation"},
                {CHAPTER_5, "Notifications", "notifications.html", "Notifications"},
                {CHAPTER_5, "Get user media", "get-user-media.html", "Get user media"},
                {CHAPTER_5, "Multilanguage", "multilanguage.html", "Multilanguage page"},
                {CHAPTER_5, "Console logs", "console-logs.html", "Console logs"},

                {CHAPTER_7, "Login form", "login-form.html", "Login form"},
                {CHAPTER_7, "Slow login", "login-slow.html", "Slow login form"},

                {CHAPTER_8, "Random calculator", "random-calculator.html", "Random calculator"},

                {CHAPTER_9, "Download files", "download.html", "Download files"},
                {CHAPTER_9, "A/B Testing", "ab-testing.html", "A/B Testing"},
                {CHAPTER_9, "Data types", "data-types.html", "Data types"},
        };
    }

    @Test(dataProvider = "getData")
    public void getTitleAndUrlTest(String chapterName, String chapterLink, String url, String title) {
        WebElement chapterLinksBlock = driver.findElement(By.xpath("//h5[text()='%s']".formatted(chapterName)));
        chapterLinksBlock.findElement(By.xpath("//a[text()='%s']".formatted(chapterLink))).click();
        if (!driver.findElements(By.name("frame-header")).isEmpty()) {
            driver.switchTo().frame("frame-header");
        }
        Assert.assertEquals(driver.findElement(By.xpath("(//h1)[2]")).getText(), title);
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + url);
    }
}
