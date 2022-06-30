import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
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
        softAssert.assertTrue(googlePage.doesTheMainResultContainKeyWord(theBeatles), "The main results does not contain " + theBeatles);
        softAssert.assertTrue(googlePage.doesThePeopleAlsoAskContainKeyWord(theBeatles), "The People Also Ask does not contain " + theBeatles);
        softAssert.assertTrue(googlePage.doesTheVideosContainKeyWord(theBeatles), "The Videos does not contain " + theBeatles);
        softAssert.assertTrue(googlePage.doesTopStoriesContainKeyWord(theBeatles), "The Top Stories does not contain " + theBeatles);
        softAssert.assertTrue(googlePage.doesSearchBoxRemainKeyWord(theBeatles));
        System.out.println("=================================");
        System.out.println("Hello, This is me!!!");
        softAssert.assertAll();
    }

    @Test
    public void testYoutube() {
        String pauseTime = "0:10";
        googlePage.openTheFirstVideoResult();
        youtubePage.playVideo();
        youtubePage.pauseVideoAt(pauseTime);
        System.out.println("******************");
        System.out.println("Hiwiwi");
        Assert.assertTrue(youtubePage.getCurrentTime().equals(pauseTime), "Pause video at the wrong time");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownTestBase() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}
