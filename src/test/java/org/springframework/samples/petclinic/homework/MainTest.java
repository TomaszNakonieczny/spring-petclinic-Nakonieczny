package org.springframework.samples.petclinic.homework;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(DataProviderRunner.class)

public class MainTest {

    private static final String PAGE_URL = "http://localhost:8080/";

    private WebDriver driver;

    private AddOwnerPage addOwnerPage;

    @DataProvider
    public static Object[][] testDataForRegisterOwner() {
        return new String[][]{
            new String[]{"Tomasz", "Testowy", "Przemyska", "Gdańsk", "508050222", "Koksik", "1990-02-20", "bird"},//wygodzenie
            new String[]{"Jan", "Wiejski", "Łódzka", "Gdańsk", "508080934", "Maksik", "1995-01-10", "cat"},//waga prawidlowa
            new String[]{"Maria", "Laskowska", "Uliczna 5", "Warszawa", "108080934", "Filut", "2000-05-12", "dog"},//niedowaga
            new String[]{"Aleks", "Malicki", "Fajna 8", "Gdańsk", "208080934", "Baks", "2001-06-11", "hamster"}, //wychudzenie
            new String[]{"Stanisław", "Kowalski", "Rajsk 2/3", "Katowice", "408080934", "Kitek", "2005-02-13", "lizard"}, //nadwage
            new String[]{"Zenobia", "Kit", "Stokrotki", "Gdańsk", "808080934", "Bunio", "2010-07-19", "snake"}, //I stopien otylosci
            new String[]{"Wiesław", "Klon", "Góra", "Poznań", "708080934", "Adolf", "2011-09-23", "dog"}, //II stopien otylosci
            new String[]{"Zbyszek", "Zbyszkowski", "Stok", "Gdańsk", "608080934", "Glut", "2012-03-08", "dog"}, //III stopien otyosci

        };
    }

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/home/tomaszn/IdeaProjects/projects/spring-petclinic/src/test/java/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        addOwnerPage = PageFactory.initElements(driver, AddOwnerPage.class);
        //driver.get("http://localhost:8080/");
        driver.get(PAGE_URL);
    }

    @Test
    @UseDataProvider("testDataForRegisterOwner")
    public void registerNewUserTest(String firstName, String lastName, String address, String city, String telephone, String petName, String date, String typeOfAnimal) {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        //add owner process
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-navbar > ul > li:nth-child(3) > a")));
        addOwnerPage.clickFindOwners();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-default")));
        addOwnerPage.clickAddOwner();
        addOwnerPage.registerOwnerData(firstName, lastName, address, city, telephone);
        addOwnerPage.clickAddOwner1();

        //add pet process
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div > div > a:nth-child(4)")));
        addOwnerPage.clickAddNewPet();
        addOwnerPage.registerPetDate(petName, date);
        addOwnerPage.chooseAnimalType(typeOfAnimal);
        addOwnerPage.clickButtonAddPet();

        //search by owner process
        addOwnerPage.clickFindOwners();
        addOwnerPage.typeInLastNameToSearch(lastName);
        addOwnerPage.clickButtonFindOwner();


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#vets > tbody > tr:nth-child(1) > td:nth-child(1)")));
        List<WebElement> listOfDocuments = driver.findElements(By.cssSelector("#vets > tbody > tr:nth-child(1) > td:nth-child(1)"));

        List<String> listOfTexts = new ArrayList<>();
        for (WebElement e : listOfDocuments) listOfTexts.add(e.getText());

        Assertions.assertThat(listOfTexts).contains(firstName + " " + lastName);
        Assertions.assertThat(listOfTexts).doesNotContain("Zenon Martyniuk");

    }

    @After
    public void tearDown() {
        driver.close();
    }

}


