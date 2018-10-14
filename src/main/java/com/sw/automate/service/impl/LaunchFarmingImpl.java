package com.sw.automate.service.impl;

import com.sw.automate.pojo.Action;
import com.sw.automate.pojo.ActionSouris;
import com.sw.automate.pojo.Delay;
import com.sw.automate.service.LaunchFarming;
import com.sw.automate.utils.SourisActionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaunchFarmingImpl implements LaunchFarming {


    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchFarmingImpl.class);

    @Value("${action.delay.default:1500}")
    private int defaultDelaiValue;

    @Override
    @Async("asyncExecutor")
    public void launchFarming(final int iterations) {
        List<Action> actionsAExecuter = new ArrayList<>();

        // attends 10s
        Action attenteDebut = new Delay(10000);
        actionsAExecuter.add(attenteDebut);
        actionsAExecuter.add(getDefaultDelai());

        // click play
        Action clickPlay = new ActionSouris(1571, 740, SourisActionEnum.CLICK, "click play");
        actionsAExecuter.add(clickPlay);
        actionsAExecuter.add(new Delay(6000));

        // click play
        Action clickAuto = new ActionSouris(370, 969, SourisActionEnum.CLICK, "click play");
        actionsAExecuter.add(clickAuto);

        for(int i =0 ; i< iterations; i++) {
            LOGGER.info("########## debut itération : {}", i);
            // attends
            Action attenteDeroulement = new Delay(60000);
            actionsAExecuter.add(attenteDeroulement);

            // recupère les recompenses
            Action clickMilieu = new ActionSouris(942, 532, SourisActionEnum.CLICK, "click milieu");
            actionsAExecuter.add(clickMilieu);
            actionsAExecuter.add(getDefaultDelai());
            Action clickCoffre = new ActionSouris(914, 642, SourisActionEnum.CLICK, "click coffre");
            actionsAExecuter.add(clickCoffre);
            actionsAExecuter.add(getDefaultDelai());

            Action vendre = new ActionSouris(788, 834, SourisActionEnum.CLICK, "Vend gemme");
            actionsAExecuter.add(vendre);
            actionsAExecuter.add(getDefaultDelai());

            Action clickOk = new ActionSouris(836, 801, SourisActionEnum.CLICK, "click ok");
            actionsAExecuter.add(clickOk);
            actionsAExecuter.add(getDefaultDelai());


            // relance
            Action clickreplay = new ActionSouris(622, 580, SourisActionEnum.CLICK, "click replay");
            actionsAExecuter.add(clickreplay);
            actionsAExecuter.add(getDefaultDelai());
        }


        actionsAExecuter.stream().forEach(Action::execute);
    }


    private Action getDefaultDelai() {
        return new Delay(defaultDelaiValue);
    }
}
