package tests;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.IndustrialSuppliesPage;
import pages.InsideIndustrialSupplies;
import utils.Driver;
import utils.SeleniumUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class IndustrialSuppliesTests extends TestBase{


    @Test (groups = {"smoke"})
    public void mainPageTest (){

        IndustrialSuppliesPage industrialSuppliesPage = new IndustrialSuppliesPage();
        industrialSuppliesPage.clickIndustrialLink();
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://www.webstaurantstore.com/industrial-supplies.html");

    }

    @Test
    public void itemsSearch(){
        mainPageTest();
        InsideIndustrialSupplies insidePage = new InsideIndustrialSupplies();
        insidePage.lookForItems("tools");
        Assert.assertTrue(insidePage.getMessage().isDisplayed());
    }
    @Test
    public void itemsSize(){
        itemsSearch();
        InsideIndustrialSupplies insidePage = new InsideIndustrialSupplies();
        List<WebElement> list = insidePage.getList();
        int numberItems = insidePage.getList().size();
        Assert.assertEquals(list.size(),numberItems);

    }

    @DataProvider(parallel = true)
    public Object[][] getData(){

        return new Object[][] {
                {"Maintenance"},
                {"Tools"},
                {"Storage"},
        };

    }

    @Test (dataProvider = "getData")
    public void testSearch(String searchTerm){
        mainPageTest();
        new InsideIndustrialSupplies().lookForItems(searchTerm);
        Assert.assertTrue(Driver.getDriver().getTitle().contains(searchTerm));
    }

    @Test
    public void scrollPage(){
        mainPageTest();
      SeleniumUtils.scroll(10000,500);
    }

    @Test
    public void scrollToElement() {
        mainPageTest();
        SeleniumUtils.scrollToElement(new InsideIndustrialSupplies().getFooterPrivacy());

    }


}
