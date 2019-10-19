package com.xuelin.coke.demo;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.xuelin.coke.domain.Car;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * TODO
     * 已经符合要求了，单元测试还是未通过
     *
     * java.lang.AssertionError:
     * Expected :1
     * Actual   :0
     * <Click to see difference>
     *
     */
    @Test
    public void manufacturerIsNull() {
        Car car = new Car( "Abc", "DD-AB-123", 4 );

        Set<ConstraintViolation<Car>> constraintViolations = validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "may not be null", constraintViolations.iterator().next().getMessage() );
    }
}

