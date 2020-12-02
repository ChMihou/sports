package com.physical.movement.utils;

import org.springframework.stereotype.Component;


/**
 * token
 */
@Component
public interface TokenGenerator {

    public String generate(String... strings);

}
