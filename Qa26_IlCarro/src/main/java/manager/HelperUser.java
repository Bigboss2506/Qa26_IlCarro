package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//*[text()=' Log in ']"));

    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"),email);
        type(By.id("password"), password);
    }
    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"),user.getPassword());
    }

    public void submit() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void clickOkButton() {
        if(!isDisabled(By.xpath("//button[(@type='submit')]"))) {
            click(By.xpath("//button[text()='Ok']"));
        }

    }




    public String getMessage() {
        pause(1000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();

//        WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
//        String text = element.getText();
//        return text;
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }

    public String getEmail() {
        pause(2000);

        return wd.findElement(By.xpath("//div[(@class='ng-star-inserted' or @class='error')]")).getText();
    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));


        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;

    }


    //**********************Registration*****************

    public void openRegistrationForm(){
        click(By.xpath("//*[text()=' Sign up ']"));

    }

    public void fillRegistrationForm(User user){
        type(By.id("name"),user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy(){
      //  click(By.id("terms-of-use")); 0*0


        // variant 2
    //    click(By.cssSelector("label[for='terms-of-use']"));

        //variant 3
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");


    }
}