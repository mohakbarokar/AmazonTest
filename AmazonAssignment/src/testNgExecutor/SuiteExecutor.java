package testNgExecutor;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

/**
 * This class will execute the testNG suite
 */
public class SuiteExecutor {
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")+"/src/suites/AmazonTestSuite.xml";
        System.out.println("Path:"+filePath);

        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(filePath);
        testng.setTestSuites(suites);
        testng.run();
    }
}
