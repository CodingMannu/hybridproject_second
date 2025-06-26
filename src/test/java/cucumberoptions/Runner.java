package cucumberoptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, glue = { "stepDefinitions",
		"appHooks" }, dryRun = false, monochrome = true, plugin = { "pretty",
				"json:target/jsonReports/cucumber-report.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failed_scenarios.txt", "html:target/HtmlReports/report.html", },

		tags = "@Login_TC_021"
)

public class Runner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}


//tags = "@Login_TC_031 or @Login_TC_032 or @Login_TC_033 or @Login_TC_034 or @Login_TC_035 or @Login_TC_036 or @Login_TC_037 or @Login_TC_038 or @Login_TC_039 or @Login_TC_040"
