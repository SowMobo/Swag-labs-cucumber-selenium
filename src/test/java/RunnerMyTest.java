import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@RunWith(Cucumber.class)
@CucumberOptions(
        /**
         *  tags = {"@OrderProduct", "@fail-login-test", "@Login"},
         *  run a test that is tagged by @OrderProduct and @fail-login-test and @Login
         *  tags = {"@OrderProduct, @fail-login-test, @Login"},
        */
        tags = {"@mobo_cucumber"},
        features = "src/test/resources/features",
//        glue = "StepDefinitions",
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports/cucumber-pretty",
//                "json:target/cucumber-reports/CucumberTestReport.json",
//                "junit:target/cucumber-reports/CucumberTestReport.xml"
//        }

        plugin = { "pretty", "html:target/cucumber-reports","json:target/cucumber.json" }
)
public class RunnerMyTest {
    @AfterClass
    public static void importTestExecutionToJIRAXray() throws IOException, NoSuchAlgorithmException, KeyStoreException, InterruptedException, KeyManagementException {
        ImportResultToXray apiConnector = new ImportResultToXray();
        apiConnector.ImportToXray();
    }
}
