package top.testeru;

import org.slf4j.Logger;

import java.io.Closeable;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/20 1:59 PM
 */
public class MySUT implements Closeable {

    static final Logger log = getLogger(lookup().lookupClass());

    String name;
    String id;

    public MySUT(String name) {
        this.name = name;
        log.info("{} created", name);
    }

    public void initId() {
        id = UUID.randomUUID().toString();
        log.info("Id created: {}", id);
    }

    public void releaseId() {
        if (id == null) {
            throw new IllegalArgumentException(name + " not initilized");
        }
        log.info("Id released: {}", id);
        id = null;
    }

    //连续添加
    public int sum(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u == 100)){
            log.warn("integer is 100！");
            throw new NumberFormatException("integer is 100！");
        }else if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            log.warn("请输入范围内的整数");
            throw new IllegalArgumentException("请输入范围内的整数！");

        }else {
            return IntStream.of(numbers).sum();
        }

    }


    //从100进行减法
    public int subtract(int... numbers) {
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            log.warn("请输入范围内的整数");
            throw new IllegalArgumentException("请输入范围内的整数！");
        }else {
            return IntStream.of(numbers).reduce(100, (a, b) -> a-b);
        }
    }

    public int subtract(int x,int y) {
        if(x>99 | x<-99 | y>99 | y<-99){
            log.warn("请输入范围内的整数");
            return 0;
        }else {
            return x-y;

        }
    }


    //平均值 average
    public double average(int... numbers) {
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            log.warn("请输入范围内的整数");
            return 0;
        }else {
            return IntStream.of(numbers).average().getAsDouble();
        }
    }

    public String concatenate(String... words) {
        return String.join(" ", words);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void close() {
        log.info("{} closed", name);
    }

}
