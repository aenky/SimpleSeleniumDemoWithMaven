import io.github.bonigarcia.wdm.WebDriverManager;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Demo {

    private WebDriver driver;
     public void initializeBrowser(){
         WebDriverManager.chromedriver().setup();
         driver=new ChromeDriver();
         System.out.println("Initialize Chrome Browser.");
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
     }

    public void openURL(){
        System.out.println("Open 'https://www.google.com/' URL");
         driver.get("https://www.google.com/");
         pause(2);
    }

    public void searchTextFunctionality(){
         //Verify Google logo
        System.out.println("Verify Google logo.");
        verifyElementIsDisplayed(driver.findElement(By.id("hplogo")));

         //Enter text in to the google search box
        System.out.println("Enter 'Hello' text in to the google search box.");
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Hello");

        //Click on first search option
        System.out.println("Click on first search option.");
        driver.findElement(By.xpath("//ul[@class='erkvQe']/li[1]")).click();
        pause(2);

        //Verify Text is entered on search text box
        System.out.println("Verify Text is entered on search text box.");
        verifyElementValue(driver.findElement(By.xpath("//input[@title='Search']")),"hello");

        pause(2);

    }

    public void verifyElementIsDisplayed(WebElement element){
        assertThat(element.isDisplayed()).isEqualTo(true);
    }

    public void verifyElementValue(WebElement element,String verifyText){
        assertThat(element.getAttribute("value")).isEqualToIgnoringCase(verifyText);

    }

    public void pause(int sec){
        try {
            Thread.sleep(1000 * sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeBrowser(){
         driver.close();
        System.out.println("Close Chrome Browser.");
    }

    public static void main(String[] args) {
        Demo demo=new Demo();
        demo.initializeBrowser();
        demo.openURL();
        demo.searchTextFunctionality();
        demo.closeBrowser();
    }



}
