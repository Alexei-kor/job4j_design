package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        int i = 5;
        float f = 7.6777f;
        double d = 7.23232345;
        char c = 'a';
        boolean b = true;
        byte by = 34;
        short sh = 28000;
        long l = 3445454534535345934L;
        LOG.debug("User info int: {}, float: {}, double: {}, char: {}, boolean: {}, byte: {}, short: {}, long: {}", i, f, d, c, b, by, sh, l);
    }
}
