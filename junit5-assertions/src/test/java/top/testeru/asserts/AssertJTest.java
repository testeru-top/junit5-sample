package top.testeru.asserts;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: assertj-core
 * @Version 1.0
 * @create: 2022/6/20 2:08 PM
 */
public class AssertJTest {

    final Logger log = getLogger(lookup().lookupClass());

    @Test
    void test() {
        log.debug("Assertions using AssertJ");

        int sum = 1 + 1;

        assertThat(sum).isGreaterThan(1).isLessThan(3);
        assertThat(sum).isEqualTo(2);
    }
}
