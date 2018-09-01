package org.coursera.ita.joaopaulo.forum.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestesFuncionais {
    
    private static WebDriver driver;  
    private static String baseUrl;
    
    @BeforeClass
    public static void setUp() {        
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/Forum";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);        
    }
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
        
    @Test
    public void deveFazerLoginComSucesso() {     
        
        driver.get(baseUrl + "/login");             
        
        driver.findElement(By.id("login")).sendKeys("joao");     
        driver.findElement(By.id("senha")).sendKeys("abc123");
        driver.findElement(By.id("entrar")).click();
        
        assertEquals("Tópicos", driver.findElement(By.tagName("h1")).getText());                
    }
    
    @Test
    public void deveFalharAoTentarFazerLogin() {     
        
        driver.get(baseUrl + "/login");             
        
        driver.findElement(By.id("login")).sendKeys("joao");     
        driver.findElement(By.id("senha")).sendKeys("abc13");
        driver.findElement(By.id("entrar")).click();
        
        assertEquals("Usuário ou senha inválida.", driver.findElement(By.tagName("p")).getText());                
    }
    
    
    @Test
    public void deveFalharAoCadastrarUsuarioSemInformarValores() {     
        
        driver.get(baseUrl + "/cadastro");             
        
        driver.findElement(By.id("cadastrar")).click();
        
        WebElement ul = driver.findElement(By.tagName("ul"));
        List<WebElement> li = ul.findElements(By.tagName("li"));
        
        assertEquals("O nome é obrigatório", li.get(0).getText());               
        assertEquals("O e-mail é obrigatório", li.get(1).getText());
        assertEquals("O login é obrigatório", li.get(2).getText());
        assertEquals("A senha é obrigatória", li.get(3).getText());
    }
    
    
    
    
}

