package com.sw.automate.service.impl;

import com.sw.automate.pojo.Action;
import com.sw.automate.pojo.ActionSouris;
import com.sw.automate.pojo.Delay;
import com.sw.automate.pojo.Scrool;
import com.sw.automate.service.LaunchTimedMouse;
import com.sw.automate.utils.DirectionEnum;
import com.sw.automate.utils.SourisActionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaunchTimedMouseImpl implements LaunchTimedMouse {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchTimedMouseImpl.class);

    private static final Action DELAI_1S = new Delay(1500);
    @Override
    public void launchJourna() {
        List<Action> actionsAExecuter = new ArrayList<>();
        int magicX = 662;
        int magicY = 685;
        int secondDungeonTimems = 0;
        LocalDate localDate = LocalDate.now();
        switch(localDate.getDayOfWeek()) {
            case MONDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = 120000;
                break;
            case TUESDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = 150000;
                break;
            case WEDNESDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = 120000;
                break;
            case THURSDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = 120000;
                break;
            case FRIDAY:
                magicX = 521;
                magicY = 887;
                secondDungeonTimems = 120000;
                break;
            case SATURDAY:
                magicX = 521;
                magicY = 887;
                secondDungeonTimems = 120000;
                break;
            case SUNDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = 190000;
                break;
        }


        Action attenteDebut = new Delay(10000);
        actionsAExecuter.add(attenteDebut);

        // lancement nox
        LOGGER.info("##############   Lancement NOX");
        Action lancementNox = new ActionSouris(237, 938, SourisActionEnum.DOUBLECLICK, "lancement nox");
        Action delai1 = new Delay(50000);

        actionsAExecuter.add(lancementNox);
        actionsAExecuter.add(delai1);

        LOGGER.info("##############   Passage plein écran");
        Action pleinecran = new ActionSouris(1558, 156, SourisActionEnum.DOUBLECLICK, "lancement nox");
        Action delaiPE = new Delay(1000);

        actionsAExecuter.add(pleinecran);
        actionsAExecuter.add(delaiPE);

        // lancementSW
        LOGGER.info("##############   Lancement SW");
        Action lancementSw = new ActionSouris(1225, 512, SourisActionEnum.CLICK, "lancement SW");
        Action delai2 = new Delay(45000);

        actionsAExecuter.add(lancementSw);
        actionsAExecuter.add(delai2);

        // les pubs
        LOGGER.info("##############   Gestion des pubs");
        gestionPubsLancementSw(actionsAExecuter);

        // click touch to start
        LOGGER.info("##############   Touch to start");
        Action clicktouchstart = new ActionSouris(1786, 457, SourisActionEnum.CLICK, "clickTouch");
        actionsAExecuter.add(clicktouchstart);
        actionsAExecuter.add(DELAI_1S);


        // ferme les achats
        LOGGER.info("##############   Fermeture des achats");
        Action close = new ActionSouris(928, 966, SourisActionEnum.CLICK, "ferme fenetre d'achat");
        actionsAExecuter.add(close);
        actionsAExecuter.add(DELAI_1S);

        // click ok journa
        LOGGER.info("##############   Validation récompenses journalieres");
        Action okJourna = new ActionSouris(1497,83, SourisActionEnum.CLICK,"validation récompenses journa");
        actionsAExecuter.add(okJourna);
        actionsAExecuter.add(DELAI_1S);

        // social point
        LOGGER.info("##############   Récupère ses social point");
        gestionSocialPoint(actionsAExecuter);

        // récupération et sélection amis
        LOGGER.info("##############   Envoi les socila points");
        gestionDesAmis(actionsAExecuter);

        // wish
        LOGGER.info("##############   Fait le wish");
        gestionDuWish(actionsAExecuter);

        // magic
        LOGGER.info("##############   Lancement magic 8");
        gestionLancementMagicB10(actionsAExecuter, magicX, magicY);
        validerRecompensesDonjon(actionsAExecuter);

        // second donjon :
        LOGGER.info("##############   Lancement second donjon");
        lancementseconddonjon(actionsAExecuter, secondDungeonTimems);
        validerRecompensesDonjon(actionsAExecuter);

        // geant
        LOGGER.info("##############   Lancement géant");
        gestionLancementGeantB10(actionsAExecuter);


        actionsAExecuter.stream().forEach(Action::execute);

    }

    private void validerRecompensesDonjon(List<Action> actionsAExecuter) {
        Action clickOk = new ActionSouris(964, 882, SourisActionEnum.CLICK, "click ok");
        Action clickCoffre = new ActionSouris(910, 584, SourisActionEnum.CLICK, "click coffre");
        Action clickOkCoffre = new ActionSouris(951, 909, SourisActionEnum.CLICK, "click ok coffre");
        Action clickOk2Coffre = new ActionSouris(951, 807, SourisActionEnum.CLICK, "click ok coffre");
        Action clickNon = new ActionSouris(1262,572, SourisActionEnum.CLICK, "click non");
        Action fermerpub = new ActionSouris(1612,193, SourisActionEnum.CLICK, "fermer pub");
        Action validerfermerpub = new ActionSouris(772, 699, SourisActionEnum.CLICK, "valider fermer pub");


        actionsAExecuter.add(clickOk);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickCoffre);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickOkCoffre);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickOk2Coffre);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickNon);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(fermerpub);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(validerfermerpub);
        actionsAExecuter.add(DELAI_1S);

    }


    private void lancementseconddonjon(List<Action> actionsAExecuter, int executionTime) {
        Action clickDonjon = new ActionSouris(521,887, SourisActionEnum.CLICK, "click donjon");
        Action clickMagicM8 = new ActionSouris(1486,621, SourisActionEnum.CLICK, "click M8");
        Action clickFriendList = new ActionSouris(155, 904, SourisActionEnum.CLICK, "click friends");
        Action launchB10 = new ActionSouris(1571, 742, SourisActionEnum.CLICK, "launch battle M8");
        Action waitB10Battle = new Delay(executionTime);

        actionsAExecuter.add(clickDonjon);
        actionsAExecuter.add(DELAI_1S);
        descendretableaudonjon(actionsAExecuter);
        actionsAExecuter.add(clickMagicM8);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickFriendList);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(launchB10);
        actionsAExecuter.add(waitB10Battle);
    }

    private void gestionLancementMagicB10(List<Action> actionsAExecuter, int x, int y) {
        Action clickBattle = new ActionSouris(1050, 960, SourisActionEnum.CLICK, "click battle");
        Action clickCairos = new ActionSouris(1050, 960, SourisActionEnum.CLICK, "click cairos");

        Action clickdonjons = new ActionSouris(482,731, SourisActionEnum.CLICK, "click cairos avant scroll");

        actionsAExecuter.add(clickBattle);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickCairos);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickdonjons);
        actionsAExecuter.add(DELAI_1S);

        // On descend en bas
        scroll(actionsAExecuter, DirectionEnum.BAS);

        descendretableaudonjon(actionsAExecuter);
        Action clickMagic = new ActionSouris(x,y, SourisActionEnum.CLICK, "click magic");
        Action clickMagicM8 = new ActionSouris(1486,621, SourisActionEnum.CLICK, "click M8");
        Action clickFriendList = new ActionSouris(155, 904, SourisActionEnum.CLICK, "click friends");
        Action launchB10 = new ActionSouris(1571, 742, SourisActionEnum.CLICK, "launch battle M8");
        Action delayLaunchB10 = new Delay(10000);
        Action clickAuto = new ActionSouris(380, 971, SourisActionEnum.CLICK, "lance l'auto");
        Action waitB10Battle = new Delay(120000);

        actionsAExecuter.add(clickMagic);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickMagicM8);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickFriendList);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(launchB10);
        actionsAExecuter.add(delayLaunchB10);
        actionsAExecuter.add(clickAuto);
        actionsAExecuter.add(waitB10Battle);
    }

    private void descendretableaudonjon(List<Action> actionsAExecuter) {

        Action clickMagic1 = new ActionSouris(1092,633, SourisActionEnum.CLICK, "click magic");
        actionsAExecuter.add(clickMagic1);
        actionsAExecuter.add(DELAI_1S);
        // On descend en bas
        scroll(actionsAExecuter, DirectionEnum.BAS);
        actionsAExecuter.add(DELAI_1S);

    }

    private void gestionLancementGeantB10(List<Action> actionsAExecuter) {
        Action clickdonjons = new ActionSouris(482,731, SourisActionEnum.CLICK, "click cairos avant scroll");

        actionsAExecuter.add(clickdonjons);
        actionsAExecuter.add(DELAI_1S);


        scroll(actionsAExecuter, DirectionEnum.HAUT);
        Action clickGeant = new ActionSouris(532, 371, SourisActionEnum.CLICK, "click B10");
        Action clickB10 = new ActionSouris(1481, 895, SourisActionEnum.CLICK, "click B10");
        Action clickFriendList = new ActionSouris(155, 904, SourisActionEnum.CLICK, "click friends");
        Action launchB10 = new ActionSouris(1571, 742, SourisActionEnum.CLICK, "launch battle B10");
        Action delayLaunchB10 = new Delay(198000);

        actionsAExecuter.add(clickGeant);
        actionsAExecuter.add(DELAI_1S);
        descendretableaudonjon(actionsAExecuter);
        actionsAExecuter.add(clickB10);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickFriendList);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(launchB10);
    }

    private void gestionDuWish(List<Action> actionsAExecuter) {
        Action clickWishBatiment = new ActionSouris(409, 523, SourisActionEnum.CLICK, "click wish batiment");
        Action clickWish2 = new ActionSouris(1403, 954 ,SourisActionEnum.CLICK, "click wish");
        Action launchWhish = new ActionSouris(1454, 456, SourisActionEnum.CLICK, "launch wish");
        Action validerLaunch = new ActionSouris(788,642, SourisActionEnum.CLICK, "valide le lancement du wish");
        Action delaiWish = new Delay(17000);
        Action closeWhish = new ActionSouris(1768, 109, SourisActionEnum.CLICK, "close wiwh");

        actionsAExecuter.add(clickWishBatiment);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickWish2);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(launchWhish);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(validerLaunch);
        actionsAExecuter.add(delaiWish);
        actionsAExecuter.add(closeWhish);
        actionsAExecuter.add(DELAI_1S);
    }

    private void gestionDesAmis(List<Action> actionsAExecuter) {
        Action clickCommunity = new ActionSouris(1570, 947, SourisActionEnum.CLICK, "clique sur community");

        Action clickFriend = new ActionSouris(254, 192, SourisActionEnum.CLICK, "clique sur friends");

        Action clickgetall = new ActionSouris(872, 332, SourisActionEnum.CLICK, "collectall");
        Action clickClose = new ActionSouris(944, 638, SourisActionEnum.CLICK, "click close");
        Action closefriends = new ActionSouris(1802,65, SourisActionEnum.CLICK, "close pop up friends");

        actionsAExecuter.add(clickCommunity);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickFriend);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickgetall);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickClose);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(closefriends);
        actionsAExecuter.add(DELAI_1S);
    }

    private void gestionSocialPoint(List<Action> actionsAExecuter) {
        Action clicksocialpoint = new ActionSouris(1134, 143, SourisActionEnum.CLICK, "social point");
        Action clickTous = new ActionSouris(1485, 263, SourisActionEnum.CLICK, "collect all");
        Action fermeSocial = new ActionSouris(1658,188, SourisActionEnum.CLICK, "ferme fenetre sociale");

        actionsAExecuter.add(clicksocialpoint);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(clickTous);
        actionsAExecuter.add(DELAI_1S);
        actionsAExecuter.add(fermeSocial);
        actionsAExecuter.add(DELAI_1S);
    }

    private void scroll(List<Action> actionsAExecuter, DirectionEnum direction) {
        Action scrool = new Scrool(direction);
        Action delai = new Delay(200);
        for(int i = 0 ; i <5; i++) {
            actionsAExecuter.add(scrool);
            actionsAExecuter.add(delai);
        }

    }

    private void gestionPubsLancementSw(List<Action> actionsAExecuter) {
        // validation des pubs
        Action validationpub = new ActionSouris(279, 945, SourisActionEnum.CLICK, "validation pub");
        Action delaipubs = new Delay(1000);

        for(int nbPub =0; nbPub <4; nbPub++) {
            actionsAExecuter.add(validationpub);
            actionsAExecuter.add(delaipubs);
        }
    }
}
