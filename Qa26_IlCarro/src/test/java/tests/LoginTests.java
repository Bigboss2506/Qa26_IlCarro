package tests;

import models.User;
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
    public void loginSuccess1(){
        //jeka@gmail.com
        //Qaz123456!!!

        User user = new User().setEmail("jeka@gmail.com").setPassword("Qaz123456!!!");

//        user.setEmail("jeka@gmail.com");
//        user.setPassword("Qaz123456!!!");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }


    @Test
    public void loginSuccess(){
        //jeka@gmail.com
        //Qaz123456!!!
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("jeka@gmail.com", "Qaz123456!!!");
        app.getHelperUser().submit();

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
        app.getHelperUser().submit();

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
        app.getHelperUser().submit();


        Assert.assertEquals(app.getHelperUser().getEmail(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());       //app.getHelperUser().clickOkButton();


    }

    @Test
    public void loginEmptyEmail(){
        //jekagmail.com
        //Qaz123456!!!
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("", "Qaz123456!!!");
        app.getHelperUser().submit();


        Assert.assertEquals(app.getHelperUser().getEmail(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());       //app.getHelperUser().clickOkButton();


    }
    @Test
    public void loginWrongPassword(){
        //jeka@gmail.com
        //Qaz123456
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("jeka@gmail.com", "Qaz123456");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

        //app.getHelperUser().clickOkButton();


    }

//    @Test
//    public void loginEmptyPassword(){
//        //jeka@gmail.com
//        //Qaz123456
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("jeka@gmail.com", "");
//        app.getHelperUser().submitLogin();
//
//        Assert.assertEquals(app.getHelperUser().getEmail(), "Password is required");
//        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());


//}
    @Test
    public void loginUnregisteredUser(){
        //bubu@gmail.com
        //Bubu123456$
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("bubu@gmail.com", "Bubu123456$");
        app.getHelperUser().submit();


        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

        //app.getHelperUser().clickOkButton();


    }
}