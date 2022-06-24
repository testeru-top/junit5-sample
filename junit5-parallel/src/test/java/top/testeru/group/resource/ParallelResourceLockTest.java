package top.testeru.group.resource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;

/**
 * @Classname: ParallelResourceLockTest
 * @Description: 并发测试-数据同步示例：预定义资源
 * @Date: 2022/6/15 09:29
 * @Created by top.testeru
 */
@Execution(ExecutionMode.CONCURRENT)
//@Execution(ExecutionMode.SAME_THREAD)
public class ParallelResourceLockTest {
    private Properties properties;
    @BeforeEach
    void before() {
        properties = new Properties(System.getProperties());
//        properties.putAll();
    }
    @AfterEach
    void after() {
//        System.setProperties(properties);
    }
    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES,mode = ResourceAccessMode.READ)
    void test1(){
        System.out.println(Thread.currentThread().getName()+" => ParallelResourceLockTest--test1");
        assertNull(System.getProperty("custom.property"));
    }
    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES,mode = ResourceAccessMode.READ_WRITE)
    void test2(){
        System.out.println(Thread.currentThread().getName()+" => ParallelResourceLockTest--test2");
        System.setProperty("custom.property","hello");
        assertEquals("hello",System.getProperty("custom.property"));
    }
    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES,mode = ResourceAccessMode.READ_WRITE)
    void test3(){
        System.out.println(Thread.currentThread().getName()+" => ParallelResourceLockTest--test3");
        System.setProperty("custom.property","world");
        assertEquals("world",System.getProperty("custom.property"));
    }
}
