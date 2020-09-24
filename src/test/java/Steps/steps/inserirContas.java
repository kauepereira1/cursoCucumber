package Steps.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class inserirContas {

    private WebDriver driver; // Definindo como variavel global

    @Dado("^que estou acessando a aplicação$")
    public void queEstouAcessandoAAplicação() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "c:\\Temp\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://srbarriga.herokuapp.com/");
    }

    @Quando("^informo o usuário \"([^\"]*)\"$")
    public void informoOUsuário(String arg1) throws Throwable {
        driver.findElement(By.id("email")).sendKeys(arg1); // Achando o elemento pelo ID e passando o valor
    }

    @Quando("^a senha \"([^\"]*)\"$")
    public void aSenha(String arg1) throws Throwable {
        driver.findElement(By.id("senha")).sendKeys(arg1); // Achando o elemento pelo ID e passando o valor
    }

    @Quando("^seleciono entrar$")
    public void selecionoEntrar() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^visualizo a página inicial$")
    public void visualizoAPáginaInicial() throws Throwable {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(); // Achando o elemento através do xpath
        Assert.assertEquals("Bem vindo, Kaue Pereira !", texto); // Fazendo a comparação se de fato tem o texto esperado
    }

    @Quando("^seleciono Contas$")
    public void selecionoContas() throws Throwable {
        driver.findElement(By.linkText("Contas")).click();
    }

    @Quando("^seleciono Adicionar$")
    public void selecionoAdicionar() throws Throwable {
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("^informo a conta \"([^\"]*)\"$")
    public void informoAConta(String arg1) throws Throwable {
        driver.findElement(By.id("nome")).sendKeys(arg1);
    }

    @Quando("^seleciono Salvar$")
    public void selecionoSalvar() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^a conta é inserida com sucesso$")
    public void aContaÉInseridaComSucesso() throws Throwable {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", texto);
    }

    @Então("^sou notificado que o nome da conta é obrigatório$")
    public void souNotificadoQueONomeDaContaÉObrigatório() throws Throwable {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Informe o nome da conta", texto);
    }

    @Então("^sou notificado que já existe uma conta com esse nome$")
    public void souNotificadoQueJáExisteUmaContaComEsseNome() throws Throwable {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", texto);
    }

    @Então("^recebo a mensagem \"([^\"]*)\"$")
    public void receboAMensagem(String arg1) throws Throwable {
        String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText(); // Realizando a busca de uma Div cuja a classe inicia com alert
        Assert.assertEquals(arg1, texto);
    }

    @After(order = 1) // Primeiro a ser executado
    public void screenshot(Scenario cenario) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target\\screenshots\\" + cenario.getId() + ".jpg")); // Colocando um nome diferente para cada imagem
            driver.quit();
        } catch (IOException e) {

        }

    }
    @After(order = 0)
        public void fecharBrowser(){
        driver.quit(); //Fechando o Browser
    }

}
