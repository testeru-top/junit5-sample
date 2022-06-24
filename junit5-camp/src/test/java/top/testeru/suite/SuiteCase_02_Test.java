
package top.testeru.suite;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/23 15:31
 */
@Suite
@SelectClasses({
        top.testeru.num.MySUTSubTest.class,
        top.testeru.str.MySUTStrTest.class
})
@SuiteDisplayName("bug测试用例")
//IncludeTags内的值非并集，只要有里面的某一个标签就运行
@IncludeTags({
        "bug","P0"
})
public class SuiteCase_02_Test {
}
