package com.javachen.cshop.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author june
 * @createTime 2019-07-22 14:52
 * @see
 * @since
 */
public interface EmailOutputProcessor {
    String OUTPUT = "emailOutput";

    @Output(OUTPUT)
    MessageChannel emailOutput();
}
