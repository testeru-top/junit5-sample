package top.testeru.group;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;


/**
 * @Classname: Parallel1_Test
 * @Description: 第一个并发测试类
 * @Date: 2022/6/13 18:11
 * @Created by top.testeru
 */
@Execution(SAME_THREAD)
public class Parallel1_Test {
    @Test
    void test1(){
        System.out.println(Thread.currentThread().getName()+" => Parallel1_Test--test1");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test2(){
        System.out.println(Thread.currentThread().getName()+" => Parallel1_Test--test2");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test3(){
        System.out.println(Thread.currentThread().getName()+" => Parallel1_Test--test3");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test4(){
        System.out.println(Thread.currentThread().getName()+" => Parallel1_Test--test4");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
