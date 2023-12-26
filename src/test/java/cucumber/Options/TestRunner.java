package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
//		features = "src/test/java/features/AddPlace.feature", 
		features = "src/test/java/features", 
		glue = { "stepDefinitions" }, 
		tags = "@Regression",
		monochrome = true,
//		plugin = "json:cucumber-report.json"
		plugin = "html:cucumber-report.html"

)
public class TestRunner {

}
