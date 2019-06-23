package com.message.processor.adjustments;

import com.message.processor.adjustments.exception.InvalidAdjustmentException;
import com.message.processor.adjustments.functions.Add;
import com.message.processor.adjustments.functions.Multiply;
import com.message.processor.adjustments.functions.Subtract;

import java.util.HashMap;
import java.util.Map;

public class AdjustmentFactory {
    private static final AdjustmentFactory ADJUSTMENT_FACTORY = new AdjustmentFactory();
    private static final Map<AdjustmentType, AdjustmentFunction> ADJUSTMENT_FUNCTION_MAP = new HashMap();

    static {
        ADJUSTMENT_FUNCTION_MAP.put(AdjustmentType.ADD, new Add());
        ADJUSTMENT_FUNCTION_MAP.put(AdjustmentType.SUBTRACT, new Subtract());
        ADJUSTMENT_FUNCTION_MAP.put(AdjustmentType.MULTIPLY, new Multiply());
    }

    private AdjustmentFactory() {

    }

    public static AdjustmentFactory getFactory() {
        return ADJUSTMENT_FACTORY;
    }

    public AdjustmentFunction getAdjustmentFunction(AdjustmentType adjustmentType) {
        if (ADJUSTMENT_FUNCTION_MAP.containsKey(adjustmentType)) {
            return ADJUSTMENT_FUNCTION_MAP.get(adjustmentType);
        }
        throw new InvalidAdjustmentException("Adjustment Funtion not available");
    }
}
