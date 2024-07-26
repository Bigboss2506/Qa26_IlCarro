package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Tel Aviv","7/28/2024","7/30/2024");
        app.getHelperCar().submit();
     //   Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());


    }
}
