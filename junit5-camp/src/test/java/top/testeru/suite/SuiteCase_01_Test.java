package top.testeru.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        top.testeru.num.MySUTAddTest.class,
        top.testeru.str.MySUTStrTest.class
})
@SuiteDisplayName("加法+字符串拼接测试用例")
public class SuiteCase_01_Test {
}
