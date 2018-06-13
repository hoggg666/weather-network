import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobject.WeatherNetworkPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WeatherNetworkTest {
  private static WebDriver driver;
  private static WeatherNetworkPage wnp;

    @BeforeClass
    public void setUp() {
      String baseUrl = "https://www.theweathernetwork.com/ca";
      driver = new ChromeDriver();
      driver.get(baseUrl);
      wnp = PageFactory.initElements(driver, WeatherNetworkPage.class);
    }

    @Test
    public void shouldHaveTemporatureGreaterThan15() {
      int currentTempNumber = wnp.findTemperature();

      assertThat(currentTempNumber, greaterThanOrEqualTo(15));

    }

    @Test
    public void shouldNavigateToNewsPage() {
      String currentUrl = wnp.seeAllNews();

      assertThat(currentUrl, containsString("news"));
    }
    @Test
    public void shouldUnderlinedLatestNews() {
      wnp
              .goToNewsPage()
              .seeLatestNews();

      wnp
              .isUnderlineAppear("Latest");

    }

  @Test
  public void shouldUnderlinedWorldNews() {
    wnp
            .goToNewsPage()
            .seeWorldNews();
    wnp
            .isUnderlineAppear("World");
  }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
      driver.close();
    }

  }


