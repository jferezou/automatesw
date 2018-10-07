package com.sw.automate.pojo;

import com.sw.automate.utils.DirectionEnum;
import com.sw.automate.utils.SourisActionEnum;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.InputEvent;

@Data
public class Scrool implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scrool.class);
    public Scrool(DirectionEnum direction) {
        this.direction = direction;
    }
    private DirectionEnum direction;

    @Override
    public void execute() {
        LOGGER.info("Execution de scrool vers le {}", direction);

        try {
            Robot robot = new Robot();
            switch(direction) {
                case HAUT:
                    robot.mouseWheel(-5);
                    break;
                case BAS:
                    robot.mouseWheel(5);
                    break;
                default:
                    LOGGER.error("Direction inconnue : {}", direction);
                    break;
            }



        } catch (AWTException e) {
            LOGGER.error("erreur",e);
        }

    }
}
