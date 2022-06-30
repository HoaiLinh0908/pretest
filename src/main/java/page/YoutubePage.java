package page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class YoutubePage {
    private final By videoScreen = By.xpath("//div[@id='movie_player']");
    private final By playButton = By.xpath("//button[@aria-label='Play']");
    private final By currentTime = By.className("ytp-time-current");
    private final By title = By.xpath("//div[@id='container']/h1");

    public void playVideo() {
        $(playButton).should(Condition.visible, Duration.ofSeconds(5000)).click();
    }

    public void pauseVideoAt(String time) {
        while (!getCurrentTime().equals(time)) {
            $(title).hover();
            $(videoScreen).hover();
        }
        $(videoScreen).click();
    }

    public String getCurrentTime() {
        return $(currentTime).getText();
    }
}
