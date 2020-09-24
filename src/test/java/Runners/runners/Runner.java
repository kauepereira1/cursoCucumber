package Runners.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions.SnippetType;




@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\inserirConta.feature", // Caminho da minha feature
        glue = "Steps.steps", // Caminho dos meus steps
        // tags = "~@ignore", // Utilizando a tag para execução de apenas um cenário
        plugin = "pretty", // Configurando o console para mostrar os dados conforme o pluguin
        monochrome = true, // Configurando a saido no console
        snippets = SnippetType.CAMELCASE, // Mudando o tipo do (_) para letras maiusculas do metodo
        dryRun = false,
        strict = true // Serve para ver se está faltando algum step
)
public class Runner {
    @BeforeClass // Será executado antes do cucumber e do Runner
    public static void reset(){
        System.setProperty("webdriver.chrome.driver", "c:\\Temp\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://srbarriga.herokuapp.com/");
        driver.findElement(By.id("email")).sendKeys("kauepereira_@outlook.com"); // Achando o elemento pelo ID e passando o valor
        driver.findElement(By.id("senha")).sendKeys("pwd123"); // Achando o elemento pelo ID e passando o valor
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }
}