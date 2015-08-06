package conceito.teste.unitario;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
                 features = "target/test-classes/features",
                 format = {"pretty",
                           "json:target/target_json/cucumber.json",
                           "junit:target/target_junit/cucumber.xml",
                           "html:target/cucumber"})
public class TesteRegras {

}