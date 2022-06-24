package top.testeru.group;

import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.DefaultParallelExecutionConfigurationStrategy;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * @Classname: Parallelism
 * @Description:
 * @Date: 2022/6/14 15:28
 * @Created by top.testeru
 */
public class MyCustomStrategy implements ParallelExecutionConfigurationStrategy {
    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {

        String s = configurationParameters.get("custom.love")
                .orElseThrow(
                        () -> new JUnitException(String.format("Configuration parameter '%s' must be set",
                                "custom.parallelism")));
        System.out.println("s:"+s);
        int count = StringUtils.countMatches(s, "&");
        System.out.println("count："+count);
//        int count = configurationParameters.get("custom.parallelism",
//                Integer::valueOf).orElseThrow(
//                () -> new JUnitException(String.format("Configuration parameter '%s' must be set",
//                        "custom.parallelism")));

        return new ParallelExecutionConfiguration() {
            @Override
            public int getParallelism() {
                return count;
            }

            @Override
            public int getMinimumRunnable() {
                return count;
            }

            @Override
            public int getMaxPoolSize() {
                return count;
            }

            @Override
            public int getCorePoolSize() {
                return count;
            }

            @Override
            public int getKeepAliveSeconds() {
                return count;
            }
        };
    }

}

