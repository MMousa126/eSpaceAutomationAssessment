package Assertion;

import org.testng.asserts.SoftAssert;

public class AssertUtility {

    private static SoftAssert softAssert;


    public static void assertTrue(SoftAssert softAssert, boolean condition){

        softAssert.assertTrue(condition);
    }

    public static void assertFalse(SoftAssert softAssert,boolean condition){

        softAssert.assertFalse(condition);
    }

    public static void assertEqual(SoftAssert softAssert,String actual, String expected){

        softAssert.assertEquals(actual,expected);

    }


    public static void assertAll(){

        softAssert.assertAll();
    }
}
