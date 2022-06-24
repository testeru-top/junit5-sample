package top.testeru.group;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

/**
 * @Classname: Parallel1_Test
 * @Description: 第2个并发测试类
 * @Date: 2022/6/13 18:11
 * @Created by top.testeru
 */
public class Parallel2_Test {

    @Test
    void test1(){
        System.out.println(Thread.currentThread().getName()+" => Parallel2_Test--test1");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test2(){
        System.out.println(Thread.currentThread().getName()+" => Parallel2_Test--test2");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test3(){
        System.out.println(Thread.currentThread().getName()+" => Parallel2_Test--test3");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
