package top.testeru.group.resource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import top.testeru.entity.User;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;

/**
 * @Classname: ParallelResourceLockTest
 * @Description: 并发测试-数据同步示例：自定义资源
 * @Date: 2022/6/15 09:29
 * @Created by top.testeru
 */
@Execution(ExecutionMode.CONCURRENT)
//@Execution(ExecutionMode.SAME_THREAD)
public class ParallelResourceLock2Test {
    final static String GLOBAL_USER = "top.testeru.entity.User";

    @BeforeEach
    void before() {
        User.clear();
    }
    @AfterEach
    void after() {
    }
    @Test
    @ResourceLock(value = GLOBAL_USER, mode = READ)
    void test1(){
        System.out.println(Thread.currentThread().getName()+" => isEmpty_Test() : "+User.getUsers());
        assertTrue(User.getUsers().isEmpty());
    }
    @Test
    @ResourceLock(value = GLOBAL_USER, mode = READ_WRITE)
    void test2(){
        User.add(1, "peter");
        System.out.println(Thread.currentThread().getName()+" => add_Test() : "+User.getUsers());
        assertEquals("peter", User.get(1));
    }
    @Test
    @ResourceLock(value = GLOBAL_USER, mode = READ_WRITE)
    void test3(){
        User.update(1, "john");
        System.out.println(Thread.currentThread().getName()+" => update_Test() : "+User.getUsers());
        assertEquals("john", User.get(1));
    }
    @Test
    @ResourceLock(value = GLOBAL_USER, mode = READ_WRITE)
    void test4(){
        User.add(2, "Anand");
        User.remove(2);
        System.out.println(Thread.currentThread().getName()+" => remove_Test() : "+User.getUsers());
        assertNull(User.get(2));
    }
}
