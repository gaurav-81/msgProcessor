package com.message.processor;

import com.message.processor.adjustments.Adjustment;
import com.message.processor.sale.Sale;

import java.util.Optional;

public interface Message {

    Sale getSale();

    Optional<Adjustment> getAdjustment();
}
