package org.springframework.samples.petclinic.homework;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddOwnerPage {
    @FindBy(css = "#main-navbar > ul > li:nth-child(3) > a")
    private WebElement buttonFindOwners;

    @FindBy(css = "a.btn.btn-default")
    private WebElement buttonAddOwner;

    @FindBy(css = "#add-owner-form > div:nth-child(2) > div > button")
    private WebElement buttonAddOwner1;

    @FindBy(css = "input#firstName")
    private WebElement firstNameField;

    @FindBy(css = "input#LastName")
    private WebElement lastNameField;

    @FindBy(css = "input#Address")
    private WebElement addressField;

    @FindBy(css = "input#City")
    private WebElement cityField;

    @FindBy(css = "input#Telephone")
    private WebElement telephoneField;

    @FindBy(css = "body > div > div > a:nth-child(4)")
    private WebElement buttonAddNewPet;

    @FindBy(css = "input#name")
    private WebElement petNameField;

    @FindBy(css = "input#birthDate")
    private WebElement dateField;

    @FindBy(css = "#type > option:nth-child(1)")
    private WebElement typeOfAnimal1Field;

    @FindBy(css = "#type > option:nth-child(2)")
    private WebElement typeOfAnimal2Field;

    @FindBy(css = "#type > option:nth-child(3)")
    private WebElement typeOfAnimal3Field;

    @FindBy(css = "#type > option:nth-child(4)")
    private WebElement typeOfAnimal4Field;

    @FindBy(css = "#type > option:nth-child(5)")
    private WebElement typeOfAnimal5Field;

    @FindBy(css = "#type > option:nth-child(6)")
    private WebElement typeOfAnimal6Field;

    @FindBy(css = "button.btn.btn-default")
    private WebElement buttonAddPet;

    @FindBy(css = ".form-control")
    private WebElement lastNameToSearch;

    @FindBy(css = "button.btn.btn-default")
    private WebElement buttonFindOwner;


    public void clickFindOwners() { buttonFindOwners.click(); }
    public void clickAddOwner() { buttonAddOwner.click(); }
    public void clickAddOwner1() { buttonAddOwner1.click(); }

    public void registerOwnerData(String firstName, String lastName, String address, String city, String telephone) {

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        addressField.sendKeys(address);
        cityField.sendKeys(city);
        telephoneField.sendKeys(telephone);}


    public void clickAddNewPet() { buttonAddNewPet.click(); }

    public void registerPetDate(String petName, String date) {

        petNameField.sendKeys(petName);
        dateField.sendKeys(date);
       }

    public void chooseAnimalType(String typeOfAnimal) {
        if (typeOfAnimal.equals("bird")) {
            typeOfAnimal1Field.click();
        }

        if (typeOfAnimal.equals("cat")) {
            typeOfAnimal2Field.click();
        }

        if (typeOfAnimal.equals("dog")) {
            typeOfAnimal3Field.click();
        }

        if (typeOfAnimal.equals("hamster")) {
            typeOfAnimal4Field.click();
        }

        if (typeOfAnimal.equals("lizard")) {
            typeOfAnimal5Field.click();
        }

        if (typeOfAnimal.equals("snake")) {
            typeOfAnimal6Field.click();
        }
    }

    public void clickButtonAddPet() { buttonAddPet.click(); }
    public void typeInLastNameToSearch(String lastName) {
        lastNameToSearch.sendKeys(lastName);
    }
    public void clickButtonFindOwner() { buttonFindOwner.click(); }
}




