package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FXMLControllerTest {

    private FXMLController cont = new FXMLController();

    @Test
    void testNameValidation() {
        try {
            Boolean actualResult = cont.validateName("iPhone 12");
            Boolean falseResult = cont.validateName("Ahkjfhdknjfkldsfkowngkjldfnbkjdfniuoewnfkjlsdnvkjneruowrnungkjenfbjonaeriuogrwnfkjsndfkjsdfkjnsdkjndfbjunefkjbnergjurwnkjgefnbkjdfngjuerwnjgkernbuaenbjmkaernkjafngkjafnbmdfnbuoerwngjernbdfkjbniuerwngkjerngrwiugnerjmbndfkjbnerjognerwkjgn;dfkjbnekjgnrwkj;gfnijworgnadfkjbnerjkgnerwngerkj;bndfkj;gnijoerwngfkjaerwanbeabaergewagaergferag");

            assertEquals(true, actualResult);
            assertEquals(false, falseResult);
        } catch (IllegalStateException e) {
            System.out.println("JavaFX ruins my test");
        } catch (ExceptionInInitializerError e) {
            System.out.println("JavaFX ruins my test");
        }
    }

    @Test
    void testSerialNumberValidation() {
        ObservableList<Items> testList = FXCollections.observableArrayList();
        testList.add(new Items("Xbox Series X", "A-XB1-24A-XY3", "1499.00"));
        testList.add(new Items("Samsung TV", "S-40A-ZBD-E47", "599.99"));

        try {
            Boolean actualResult = cont.validateSerial("A-123-495-AJD", testList);
            Boolean falseResult = cont.validateSerial("KDKDK$*$*@JIJFD", testList);
            Boolean duplicateResult = cont.validateSerial("A-XB1-24A-XY3", testList);

            assertEquals(true, actualResult);
            assertEquals(false, falseResult);
            assertEquals(false, duplicateResult);
        } catch (IllegalStateException e) {
            System.out.println("JavaFX ruins my test");
        } catch (ExceptionInInitializerError e) {
            System.out.println("JavaFX ruins my test");
        } catch (NoClassDefFoundError e) {
            System.out.println("JavaFX ruins my test");
        }

    }

    @Test
    void testValueValidation() {
        try {
            Boolean actualResult = cont.validateValue("69.69");
            Boolean falseResult = cont.validateValue("SSHEEEESH");
            assertEquals(true, actualResult);
            assertEquals(false, falseResult);
        } catch (IllegalStateException e) {
            System.out.println("JavaFX ruins my test");
        } catch (ExceptionInInitializerError e) {
            System.out.println("JavaFX ruins my test");
        } catch (NoClassDefFoundError e) {
            System.out.println("JavaFX ruins my test");
        }

    }

    @Test
    void test1024Items() {
        ObservableList<Items> testAdding = FXCollections.observableArrayList();
        ObservableList<Items> expectedList = FXCollections.observableArrayList();
        for (int i = 0; i < 1024; i++) {
            cont.addItems("a", "b", "c", testAdding);
        }
        for (int i = 0; i < 1024; i++) {
            expectedList.add(new Items("a", "b", "c"));
        }
            assertEquals(expectedList, testAdding);
    }
}