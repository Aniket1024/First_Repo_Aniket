package RunnerPackage;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src\\test\\resources\\SpotifyFeature"},
		glue= {"steps"},
		plugin= {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
		)
public class runner extends AbstractTestNGCucumberTests {

}
