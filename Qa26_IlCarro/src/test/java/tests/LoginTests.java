package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();

    }


    @Test
    public void loginSuccess(){
        //jeka@gmail.com
        //Qaz123456!!!
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("jeka@gmail.com", "Qaz123456!!!");
        app.getHelperUser().submitLogin();

        //Assert ---> if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        //app.getHelperUser().clickOkButton();


    }

    @Test
    public void loginSuccessModel(){
        //jeka@gmail.com
        //Qaz123456!!!
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("jeka@gmail.com", "Qaz123456!!!");
        app.getHelperUser().submitLogin();

        //Assert ---> if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        //app.getHelperUser().clickOkButton();


    }

    @Test
    public void loginWrongEmail(){
        //jekagmail.com
        //Qaz123456!!!
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("jekagmail.com", "Qaz123456!!!");
        app.getHelperUser().submitLogin();


        Assert.assertEquals(app.getHelperUser().getEmail(), "It'snot look like email");

        //app.getHelperUser().clickOkButton();


    }
    @Test
    public void loginWrongPassword(){
        //jeka@gmail.com
        //Qaz123456
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("jeka@gmail.com", "Qaz123456");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

        //app.getHelperUser().clickOkButton();


    }
    @Test
    public void loginUnregisteredUser(){
        //bubu@gmail.com
        //Bubu123456$
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("bubu@gmail.com", "Bubu123456$");
        app.getHelperUser().submitLogin();


        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

        //app.getHelperUser().clickOkButton();


    }
}