package kevin.trochon.resolveur.webservices.services;

import kevin.trochon.resolveur.entity.TermesDuCalculBaoLoc;
import kevin.trochon.resolveur.repository.CalculBaoLocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class CalculBaoLocService {

    @Autowired
    private CalculBaoLocRepository repository;

    private final int POSITION_DENOMINATEUR_1 = 7;
    private final int POSITION_DENOMINATEUR_2 = 27;

    private final int POSITION_NUMERATEUR_1 = 5;

    private final int POSITION_NUMERATEUR_2 = 25;

    private LocalTime debut;

    private LocalTime fin;

    private final int[] proposition = new int[]{1,2,3,4,5,6,7,8,9};

    private String[] baoLocPattern = new String[]{"","+","(","13","*","","/","",")","+","","+","(","12","*","",")","-","","-","11","+","(","","*","","/","",")","-","10","=",""};

    /**
     * Fonction qui assure que la case est libre.
     * @param pattern tableau représentant le calcul.
     * @param position la position dans le calcul.
     * @return vrain si la case est libre.
     */
    public static Boolean isPositionValid(String[] pattern,int position){
        if (pattern[position].equals("")){
            return true;
        }
        return false;
    }

    /**
     * Fonction qui assure que le nombre qui sera placer sera différent du suivant.
     * @param pattern tableau représentant le calcul.
     * @param value valeur a inserer dans le calcul.
     * @return vrai si le nombre n'existe pas dans le calcul
     */
    public static Boolean isNumberValid(String[] pattern,int value){
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i].equals(String.valueOf(value))){
                return false;
            } else if (i == 7 && !pattern[5].equals("") && !pattern[7].equals("") && isRest(pattern,5,7)) {
                return false;
            } else if (i == 27  && !pattern[25].equals("") && !pattern[27].equals("") && isRest(pattern,25,27)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isRest(String[] pattern, int positionNumerateur, int positionDenominateur) {
        if(!pattern[positionNumerateur].equals("") && !pattern[positionDenominateur].equals("") &&
            pattern[positionNumerateur].matches("[1-9]{1}") && pattern[positionDenominateur].matches("[1-9]{1}")){
            Integer numerateur = Integer.valueOf(pattern[positionNumerateur]);
            Integer denominateur =Integer.valueOf(pattern[positionDenominateur]);
            if(numerateur %  denominateur == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Fonction qui nettoye le pattern.
     * @param pattern tableau représentant le calcul.
     * @param position position du tableau.
     * @return le tableau sans la valeur ajoutée.
     */
    public static String[] unsetValue(String[] pattern, int position){
        pattern[position] = "";
        return pattern;
    }

    /**
     * Fonction qui affiche le calcul.
     * @param pattern tableau représentant le calcul.
     */
    private void save(String[] pattern){
        TermesDuCalculBaoLoc termesDuCalculBaoLoc = new TermesDuCalculBaoLoc();
        this.fin = LocalTime.now();
        LocalTime tempsExecution = this.fin.minusHours(this.debut.getHour()).minusMinutes(this.debut.getMinute()).minusSeconds(this.debut.getSecond()).minusNanos(this.debut.getNano());
        int[] valeur = new int[9];
        int j = 0;
        int resultat = Integer.parseInt(pattern[pattern.length - 1]);
        for (String s : pattern) {
            if (s.matches("[1-9]{1}")) {
                valeur[j] = Integer.parseInt(s);
                j += 1;
            }
            if (j == valeur.length) break;
        }
        if(valeur[2]!=0 && valeur[8]!=0){
            if((valeur[0]+(13*valeur[1]/valeur[2])+valeur[3]+(12*valeur[4])-valeur[5]-11+(valeur[6]*valeur[7]/valeur[8])-10)==resultat){
                termesDuCalculBaoLoc.setTerme1(valeur[0]);
                termesDuCalculBaoLoc.setTerme2(valeur[1]);
                termesDuCalculBaoLoc.setTerme3(valeur[2]);
                termesDuCalculBaoLoc.setTerme4(valeur[3]);
                termesDuCalculBaoLoc.setTerme5(valeur[4]);
                termesDuCalculBaoLoc.setTerme6(valeur[5]);
                termesDuCalculBaoLoc.setTerme7(valeur[6]);
                termesDuCalculBaoLoc.setTerme8(valeur[7]);
                termesDuCalculBaoLoc.setTerme9(valeur[8]);
                termesDuCalculBaoLoc.setResultat(resultat);
                termesDuCalculBaoLoc.setTempsExecution(tempsExecution);
                this.baoLocPattern = new String[]{"","+","(","13","*","","/","",")","+","","+","(","12","*","",")","-","","-","11","+","(","","*","","/","",")","-","10","=",""};
                this.repository.save(termesDuCalculBaoLoc);
            }
        }
    }

    /**
     * Fonction qui résoud le problème.
     * @param resultat resultat du calcul.
     * @return Vrai s'il y a une solution et l'affiche.
     */
    public boolean solve(String resultat,LocalTime debut){
        this.debut = debut;
        baoLocPattern[baoLocPattern.length-1] = resultat;
        if(gameOver(baoLocPattern)){
            save(baoLocPattern);
            return true;
        }
        else {
            for (int i = 0; i < baoLocPattern.length-1; i++) {
                if (isPositionValid(baoLocPattern,i)){
                    for (int j = 0; j < proposition.length; j++) {
                        if (isNumberValid(baoLocPattern,proposition[j])){
                            baoLocPattern[i] = String.valueOf(proposition[j]);
                            if(solve(resultat,debut)){
                                return true;
                            }
                        }
                    }
                    unsetValue(baoLocPattern,i);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Fonction qui détermine si le calcule est résolu.
     * @param pattern tableau représentant le calcul.
     * @return Vrai si le résultat demandé du calcul est possible.
     */
    private boolean gameOver(String[] pattern) {
        int[] valeur = new int[9];
        int j = 0;
        int resultat = Integer.parseInt(pattern[pattern.length - 1]);
        for (String s : pattern) {
            if (s.matches("[1-9]{1}")) {
                valeur[j] = Integer.parseInt(s);
                j += 1;
            }
            if (j == valeur.length) break;
        }
        if(valeur[2]!=0 && valeur[8]!=0){
            if((valeur[0]+(13*valeur[1]/valeur[2])+valeur[3]+(12*valeur[4])-valeur[5]-11+(valeur[6]*valeur[7]/valeur[8])-10)==resultat){
                return true;
            }
        }
        return false;
    }

    public CalculBaoLocRepository getRepository() {
        return repository;
    }
}
