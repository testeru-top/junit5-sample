package top.testeru.selftag;

import org.apiguardian.api.API;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

import static org.apiguardian.api.API.Status.STABLE;

/**
 * @program: junit5-sample
 * @author: testeru.top
 * @description: 自定义bug注解
 * @Version 1.0
 * @create: 2022/6/23 15:29
 */
@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tags({
        @Tag("P0"),//用例是P0级别的
        @Tag("bug")//有bug研发需要修复
})
public @interface BugTest {
}
