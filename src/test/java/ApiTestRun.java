import com.gogo.api.test.CreateUserTest;
import com.gogo.api.test.GetUserTest;
import org.testng.TestNG;

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
