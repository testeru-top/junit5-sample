package top.testeru.num;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import top.testeru.basic.BasicTest;
import top.testeru.selftag.BugTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySUTSubTest extends BasicTest {

    @Test
    @Tag("sub")
    @Tags({
            @Tag("P0"),//用例是P0级别的
            @Tag("qa1"),//测试环境为 qa1
//            @Tag("bug")//有bug研发需要修复
    })
    void sub1Test(){
        result = mySUT.subtract(20,10,30);
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(40,result, "The result of sub two numbers is wrong");
        //hamcrest断言
        assertThat( "The result of sub two numbers is wrong",result , is(equalTo(40)));
        assertThat( "The result of sub two numbers is wrong",result , is(lessThanOrEqualTo(100)));
    }

    @BugTest
    @Tag("sub")
    @Tag("qa1")
    void sub2Test(){
        result = mySUT.subtract(20,10,30);
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(20,result, "The result of sub two numbers is wrong");
        int re1 = mySUT.subtract(10,10,30);
        System.out.println(re1);
        //hamcrest断言
        assertThat( "The result of sub two numbers is wrong",result , is(equalTo(40)));
        assertThat( "The result of sub two numbers is wrong",result , is(lessThanOrEqualTo(100)));
    }
    @BugTest
    @Tag("sub")
    @Tag("qa1")
    void sub3Test(){
        result = mySUT.subtract(20,10,30);
        int re1 = mySUT.subtract(10,10,30);
        System.out.println(re1);
        assertAll(
                //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
                () -> assertEquals(40,result, "The result of sub two numbers is wrong"),
                //hamcrest断言
                () -> assertThat( "The result of sub two numbers is wrong",result , is(equalTo(40))),
                () -> assertThat( "The result of sub two numbers is wrong",result , is(lessThanOrEqualTo(100)))

        );
    }
}
