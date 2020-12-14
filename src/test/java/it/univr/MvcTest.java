package it.univr;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;

import java.util.List;

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

        int before =  tester.getElementsByXPath("//td").size(); //ritorna tutti gli elementi che hanno un nome td
        tester.clickLinkWithText("Add new person");

        tester.assertTextPresent("Create a new record"); // serve per controllare se nella pagina in cui mi trovo c'è quel testo li
        tester.setTextField("firstname","Leonardo");
        tester.setTextField("lastname","Zecchin");
        tester.submit();

        tester.assertTextPresent("People List");
        int after = tester.getElementsByXPath("//td").size();

        Assert.assertTrue(after > before);

    }

}
