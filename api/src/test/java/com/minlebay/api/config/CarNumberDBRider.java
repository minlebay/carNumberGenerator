package com.minlebay.api.config;

import com.github.database.rider.spring.DBRiderTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see DBRiderTestExecutionListener
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@TestExecutionListeners(value = CarNumberDBRiderTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface CarNumberDBRider {

  /**
   * @return name of the DataSource bean in Spring Context.
   * If empty then dataSource bean will be loaded by class and thus default one will be used.
   */
  String dataSourceBeanName() default "";
}
