package it.univr;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;

public class MvcTest {

    private WebTester tester;

    @Before
    public void prepare(){
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/");
    }

    @Test
    public void testInitialView(){
        tester.beginAt("/");
        tester.clickLinkWithText("Add new person");
    }

    @Test
    public void addNewPersonTest(){
        tester.beginAt("/");
        tester.clickLinkWithText("Add new person");

        tester.assertTextPresent("Create a new record"); // serve per controllare se nella pagina in cui mi trovo c'è quel testo li
        tester.setTextField("firstname","Leonardo");
        tester.setTextField("lastname","Zecchin");
        tester.submit();

        tester.assertTextPresent("People List");
    }

}
