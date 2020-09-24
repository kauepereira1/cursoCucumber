import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ambiente {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "c:\\Temp\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://srbarriga.herokuapp.com/");
    }
}
