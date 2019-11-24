package ohtu.systemTests;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/ohtu",
        snippets = SnippetType.CAMELCASE
)
public class RunCukesTest {

    @ClassRule
    public static ServerRule server = new ServerRule(8080);
}
