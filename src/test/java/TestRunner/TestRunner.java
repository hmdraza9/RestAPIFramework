package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
//		features = "src/test/java/features/AddPlace.feature", 
		features = "src/test/java/features", 
		glue = { "stepDefinitions" },
		plugin = { "pretty", "html:target/cucumber-reports.html"},
		tags = "@GraphQL",
		monochrome = true
//		,
//		plugin = "json:cucumber-report.json"
//		plugin ="json:target/jsonReports/cucumber-report.json"

)
public class TestRunner {

}
