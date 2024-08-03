package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"),car.getManufacture());
        type(By.id("model"),car.getModel());
        type(By.id("year"),car.getYear());
        select(By.id("fuel"),car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"),car.getCarClass());
        type(By.id("serialNumber"),car.getCarRegNumber());
        type(By.id("price"),car.getPrice() + ""); // ---> String.valueOf
        type(By.id("about"),car.getAbout());




        
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);


    }

    private void typeLocation(String location) {

        type(By.id("pickUpPlace"),location);
        click(By.cssSelector("div.pac-item"));


    }

    public void returnToHomePage() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);

    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));
        String[] from = dateFrom.split("/"); //[7][27][2024]
        String[] to = dateTo.split("/");

        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));
        click(By.xpath("//div[text()=' "+ to[1] +" ']"));


    }


//    public void searchCurrentMonth1(String city, String dateFrom, String dateTo) {
//        typeCity(city);
//        click(By.id("dates"));
//        String[] from = dateFrom.split("/"); //[7][27][2024]
//        String[] to = dateTo.split("/");
//        click(By.cssSelector("button[aria-label='Next month']"));
//        click(By.cssSelector("button[aria-label='Next month']"));
//        click(By.cssSelector("button[aria-label='Next month']"));
//        String locatorFrom = "//div[text()=' " + from[1] + " ']";
//        click(By.xpath(locatorFrom));
//        click(By.xpath("//div[text()=' "+ to[1] +" ']"));
//
//
//    }

    private void typeCity(String city) {
        clearTextField(By.id("city"));
        pause(1000);
        type(By.id("city"),city);
        pause(1000);
        click(By.cssSelector("div.pac-item"));

    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
    //    "Rehovot", "10/15/2024", "12/10/2024"
        typeCity(city);

        clearTextField(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        System.out.println(now);

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();


        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        System.out.println(from);


        int diffMonth = from.getMonthValue() - month;

        if(diffMonth>0){
            clickNextMonthBth(diffMonth);
        }

        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));
        diffMonth = to.getMonthValue() - from.getMonthValue();
        if(diffMonth>0){
            clickNextMonthBth(diffMonth);
        }

        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));




    }

    private void clickNextMonthBth(int diffMonth) {
        for (int i = 0; i<diffMonth; i++){
            click(By.cssSelector("button[aria-label='Next month']"));

        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        //"Rehovot", "9/26/2024", "3/8/2025"
        typeCity(city);
        clearTextField(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;

        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0){
            diffMonth = from.getMonthValue() - now.getMonthValue();

        }else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthBth(diffMonth);
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));


        diffYear = to.getYear() - from.getYear();
        if(diffYear == 0){
            diffMonth = to.getMonthValue() - from.getMonthValue();
        }else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthBth(diffMonth);
        click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));


        }


    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextField(By.id("dates"));
        type(By.id("dates"), dateFrom + " - " + dateTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
    }
}

