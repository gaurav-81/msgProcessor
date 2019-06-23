package com.message.processor.adjustments;

import com.message.processor.adjustments.exception.InvalidAdjustmentException;
import com.message.processor.adjustments.functions.Add;
import com.message.processor.adjustments.functions.Multiply;
import com.message.processor.adjustments.functions.Subtract;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AdjustmentFactoryTest {
    @Test
    public void testGetAddFunction() {
        AdjustmentFactory factory = AdjustmentFactory.getFactory();
        AdjustmentFunction adjustmentFunction = factory.getAdjustmentFunction(AdjustmentType.ADD);
        assertTrue(adjustmentFunction.getClass().isAssignableFrom(Add.class));
    }

    @Test
    public void testGetSubtractFunction() {
        AdjustmentFactory factory = AdjustmentFactory.getFactory();
        AdjustmentFunction adjustmentFunction = factory.getAdjustmentFunction(AdjustmentType.SUBTRACT);
        assertTrue(adjustmentFunction.getClass().isAssignableFrom(Subtract.class));
    }

    @Test
    public void testGetMultiPlyFunction() {
        AdjustmentFactory factory = AdjustmentFactory.getFactory();
        AdjustmentFunction adjustmentFunction = factory.getAdjustmentFunction(AdjustmentType.MULTIPLY);
        assertTrue(adjustmentFunction.getClass().isAssignableFrom(Multiply.class));

    }

    @Test(expected = InvalidAdjustmentException.class)
    public void testInvalidAdjustment() {
        AdjustmentFactory factory = AdjustmentFactory.getFactory();
        factory.getAdjustmentFunction(null);
    }

}
