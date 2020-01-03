import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AmazonSearch {
    ChromeDriver driver = new ChromeDriver();

    @BeforeTest
    void Launch() {
        driver.get("https://www.amazon.com/");
    }

    @Test
    void Search() {
        String searchString = "LG";
        Select SearchDropdown = new Select(driver.findElement(By.xpath("//select[@id=\"searchDropdownBox\"]")));
        SearchDropdown.selectByVisibleText("Video Games");
        WebElement searchTextbox = driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]"));
        searchTextbox.sendKeys(searchString);
        searchTextbox.sendKeys(Keys.ENTER);
        while (true) {
            List<WebElement> searchResult = driver.findElements(By.xpath("//span[@class=\"a-size-medium a-color-base a-text-normal\"]"));
            for (int j = 0; j < searchResult.size(); j++) {
                String actualString = searchResult.get(j).getText();
                System.out.println(searchResult.get(j).getText());
                Assert.assertTrue(actualString.contains(searchString));
            }
            if (driver.findElements(By.xpath("//li[@class=\"a-last\"]")).size() > 0) // Check if next button enable
            {
                driver.findElement(By.xpath("//li[@class=\"a-last\"]")).click();
            } else {
                break;
            }

        }
    }




}
