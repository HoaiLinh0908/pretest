import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.GooglePage;
import page.YoutubePage;

import static com.codeborne.selenide.Selenide.open;

public class GoogleSearchTests {

    private final GooglePage googlePage = new GooglePage();
    private final YoutubePage youtubePage = new YoutubePage();
    String theBeatles = "The Beatles";
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void beforeClass() {
        Configuration.browserSize = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--lang=en-GB");
        Configuration.browserCapabilities = options;
    }

    @BeforeMethod
    public void beforeMethod() {
        open("https://www.google.com/");
        googlePage.doGoogleSearch(theBeatles);
    }

    @Test
    public void testGoogleSearchTheBeatles() {
        softAssert.assertTrue(googlePage.doesSearchBoxRemainKeyWord(theBeatles));
        softAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownTestBase() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}
