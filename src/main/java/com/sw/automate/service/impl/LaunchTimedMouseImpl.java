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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaunchTimedMouseImpl implements LaunchTimedMouse {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchTimedMouseImpl.class);

    @Value("${action.delay.default:1500}")
    private int defaultDelaiValue;

    @Value("${action.donjon.temps.feu:120000}")
    private int tempsFeu;
    @Value("${action.donjon.temps.eau:120000}")
    private int tempsEau;
    @Value("${action.donjon.temps.vent:120000}")
    private int tempsVent;
    @Value("${action.donjon.temps.ligth:120000}")
    private int tempsLigth;
    @Value("${action.donjon.temps.dark:120000}")
    private int tempsDark;
    @Value("${action.donjon.temps.magic:120000}")
    private int tempsMagic;
    @Value("${action.donjon.temps.geant:120000}")
    private int tempsGeant;

    @Override
    @Async("asyncExecutor")
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
                secondDungeonTimems = tempsDark;
                break;
            case TUESDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = tempsFeu;
                break;
            case WEDNESDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = tempsEau;
                break;
            case THURSDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = tempsVent;
                break;
            case FRIDAY:
                magicX = 521;
                magicY = 887;
                secondDungeonTimems = tempsMagic;
                break;
            case SATURDAY:
                magicX = 521;
                magicY = 887;
                secondDungeonTimems = 120000;
                break;
            case SUNDAY:
                magicX = 525;
                magicY = 724;
                secondDungeonTimems = tempsLigth;
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
        Action pleinecran = new ActionSouris(1525, 82, SourisActionEnum.DOUBLECLICK, "lancement nox");
        Action delaiPE = new Delay(1000);

        actionsAExecuter.add(pleinecran);
        actionsAExecuter.add(delaiPE);

        // lancementSW
        LOGGER.info("##############   Lancement SW");
        Action lancementSw = new ActionSouris(1231, 519, SourisActionEnum.CLICK, "lancement SW");
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
        actionsAExecuter.add(getDefaultDelai());


        // ferme les achats
        LOGGER.info("##############   Fermeture des achats 3 fois");
        for(int i =0; i < 3; i++) {
            Action close = new ActionSouris(928, 966, SourisActionEnum.CLICK, "ferme fenetre d'achat "+i+" fois");
            actionsAExecuter.add(close);
            actionsAExecuter.add(getDefaultDelai());
        }

        // click ok journa
        LOGGER.info("##############   Validation récompenses journalieres");
        Action okJourna = new ActionSouris(1497,83, SourisActionEnum.CLICK,"validation récompenses journa");
        actionsAExecuter.add(okJourna);
        actionsAExecuter.add(getDefaultDelai());

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

    private Action getDefaultDelai() {
       return new Delay(defaultDelaiValue);
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
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickCoffre);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickOkCoffre);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickOk2Coffre);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickNon);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(fermerpub);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(validerfermerpub);
        actionsAExecuter.add(getDefaultDelai());

    }


    private void lancementseconddonjon(List<Action> actionsAExecuter, int executionTime) {
        Action clickDonjon = new ActionSouris(521,887, SourisActionEnum.CLICK, "click donjon");
        Action clickMagicM8 = new ActionSouris(1486,621, SourisActionEnum.CLICK, "click M8");
        Action clickFriendList = new ActionSouris(155, 904, SourisActionEnum.CLICK, "click friends");
        Action launchB10 = new ActionSouris(1571, 742, SourisActionEnum.CLICK, "launch battle M8");
        Action waitB10Battle = new Delay(executionTime);

        actionsAExecuter.add(clickDonjon);
        actionsAExecuter.add(getDefaultDelai());
        descendretableaudonjon(actionsAExecuter);
        actionsAExecuter.add(clickMagicM8);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickFriendList);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(launchB10);
        actionsAExecuter.add(waitB10Battle);
    }

    private void gestionLancementMagicB10(List<Action> actionsAExecuter, int x, int y) {
        Action clickBattle = new ActionSouris(1050, 960, SourisActionEnum.CLICK, "click battle");
        Action clickCairos = new ActionSouris(1050, 960, SourisActionEnum.CLICK, "click cairos");

        Action clickdonjons = new ActionSouris(482,731, SourisActionEnum.CLICK, "click cairos avant scroll");

        actionsAExecuter.add(clickBattle);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickCairos);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickdonjons);
        actionsAExecuter.add(getDefaultDelai());

        // On descend en bas
        scroll(actionsAExecuter, DirectionEnum.BAS);

        descendretableaudonjon(actionsAExecuter);
        Action clickMagic = new ActionSouris(x,y, SourisActionEnum.CLICK, "click magic");
        Action clickMagicM8 = new ActionSouris(1486,621, SourisActionEnum.CLICK, "click M8");
        Action clickFriendList = new ActionSouris(155, 904, SourisActionEnum.CLICK, "click friends");
        Action launchB10 = new ActionSouris(1571, 742, SourisActionEnum.CLICK, "launch battle M8");
        Action delayLaunchB10 = new Delay(10000);
        Action clickAuto = new ActionSouris(380, 971, SourisActionEnum.CLICK, "lance l'auto");
        Action waitB10Battle = new Delay(tempsMagic);

        actionsAExecuter.add(clickMagic);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickMagicM8);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickFriendList);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(launchB10);
        actionsAExecuter.add(delayLaunchB10);
        actionsAExecuter.add(clickAuto);
        actionsAExecuter.add(waitB10Battle);
    }

    private void descendretableaudonjon(List<Action> actionsAExecuter) {

        Action clickMagic1 = new ActionSouris(1092,633, SourisActionEnum.CLICK, "click magic");
        actionsAExecuter.add(clickMagic1);
        actionsAExecuter.add(getDefaultDelai());
        // On descend en bas
        scroll(actionsAExecuter, DirectionEnum.BAS);
        actionsAExecuter.add(getDefaultDelai());

    }

    private void gestionLancementGeantB10(List<Action> actionsAExecuter) {
        Action clickdonjons = new ActionSouris(482,731, SourisActionEnum.CLICK, "click cairos avant scroll");

        actionsAExecuter.add(clickdonjons);
        actionsAExecuter.add(getDefaultDelai());


        scroll(actionsAExecuter, DirectionEnum.HAUT);
        Action clickGeant = new ActionSouris(532, 371, SourisActionEnum.CLICK, "click B10");
        Action clickB10 = new ActionSouris(1481, 895, SourisActionEnum.CLICK, "click B10");
        Action clickFriendList = new ActionSouris(155, 904, SourisActionEnum.CLICK, "click friends");
        Action launchB10 = new ActionSouris(1571, 742, SourisActionEnum.CLICK, "launch battle B10");
        Action delayLaunchB10 = new Delay(tempsGeant);

        actionsAExecuter.add(clickGeant);
        actionsAExecuter.add(getDefaultDelai());
        descendretableaudonjon(actionsAExecuter);
        actionsAExecuter.add(clickB10);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickFriendList);
        actionsAExecuter.add(getDefaultDelai());
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
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickWish2);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(launchWhish);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(validerLaunch);
        actionsAExecuter.add(delaiWish);
        actionsAExecuter.add(closeWhish);
        actionsAExecuter.add(getDefaultDelai());
    }

    private void gestionDesAmis(List<Action> actionsAExecuter) {
        Action clickCommunity = new ActionSouris(1570, 947, SourisActionEnum.CLICK, "clique sur community");

        Action clickFriend = new ActionSouris(254, 192, SourisActionEnum.CLICK, "clique sur friends");

        Action clickgetall = new ActionSouris(872, 332, SourisActionEnum.CLICK, "collectall");
        Action clickClose = new ActionSouris(944, 638, SourisActionEnum.CLICK, "click close");
        Action closefriends = new ActionSouris(1802,65, SourisActionEnum.CLICK, "close pop up friends");

        actionsAExecuter.add(clickCommunity);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickFriend);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickgetall);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickClose);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(closefriends);
        actionsAExecuter.add(getDefaultDelai());
    }

    private void gestionSocialPoint(List<Action> actionsAExecuter) {
        Action clicksocialpoint = new ActionSouris(1134, 143, SourisActionEnum.CLICK, "social point");
        Action clickTous = new ActionSouris(1485, 263, SourisActionEnum.CLICK, "collect all");
        Action fermeSocial = new ActionSouris(1658,188, SourisActionEnum.CLICK, "ferme fenetre sociale");

        actionsAExecuter.add(clicksocialpoint);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(clickTous);
        actionsAExecuter.add(getDefaultDelai());
        actionsAExecuter.add(fermeSocial);
        actionsAExecuter.add(getDefaultDelai());
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
