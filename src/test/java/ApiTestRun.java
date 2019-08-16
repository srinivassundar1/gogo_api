import com.gogo.api.test.CreateUserTest;
import com.gogo.api.test.GetUserTest;
import org.testng.TestNG;

/***
 * Driver class used as an alternative to execute testng programatically through JAR file for consumers who don't
 * need to install maven and run TestNG
 *
 */
public class ApiTestRun {
    public static void main(String[] args) {
        TestNG testSuite = new TestNG();
        testSuite.setTestClasses(new Class[] { CreateUserTest.class, GetUserTest.class});
        testSuite.setDefaultSuiteName("GoApiTestSuite");
        testSuite.setDefaultTestName("ApiTest");
        testSuite.setOutputDirectory("testng-output");
        testSuite.run();
    }
}
