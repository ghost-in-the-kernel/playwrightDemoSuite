package Runners;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Smoke Test Suite")
@SelectPackages("example")
@IncludeTags("smoke")
public class RunConfig1 {
    //The annotations configure the test execution.
}
