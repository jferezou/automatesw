package com.sw.automate.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Delay implements Action{

    private static final Logger LOGGER = LoggerFactory.getLogger(Delay.class);
    public Delay(int delai) {
        this.delai = delai;
    }

    private int delai;

    @Override
    public void execute() {
        LOGGER.info("Attente de {} ms", delai);
        Thread thread = Thread.currentThread();
        try {
            thread.sleep(delai);
        }
        catch(InterruptedException e) {
            LOGGER.error("erreur !",e);
        }

    }
}
