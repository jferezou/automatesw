package com.sw.automate.pojo;

import com.sw.automate.utils.SourisActionEnum;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.InputEvent;

@Data
public class ActionSouris implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionSouris.class);
    public ActionSouris(int x, int y, SourisActionEnum action, String actionName) {
        this.x = x;
        this.y = y;
        this.action = action;
        this.actionName = actionName;
    }

    private int x;
    private int y;
    private SourisActionEnum action;
    private String actionName;

    @Override
    public void execute() {
        LOGGER.info("Execution de l'action {} : {} en [ {} ; {} ]", actionName, action.getCode(), x, y);

        try {
            Robot robot = new Robot();
            robot.mouseMove(x,y);
            switch(action) {
                case CLICK:
                    leftClick(robot);
                    break;
                case DOUBLECLICK:
                    doubleLeftClick(robot);
                    break;
                default:
                    LOGGER.error("Action inconnue : {}", action);
                    break;
            }



        } catch (AWTException e) {
            LOGGER.error("erreur",e);
        }

    }

    private void leftClick(final Robot robot)
    {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }

    private void doubleLeftClick(final Robot robot)
    {
        leftClick(robot);
        leftClick(robot);
    }
}
