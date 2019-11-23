package ohtu;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@TestPropertySource(
  locations = "classpath:application-test.properties")
@CucumberOptions(plugin = {"pretty"})
public class RunCukesTest {
    @ClassRule
    public static ServerRule server = new ServerRule(8080);
}