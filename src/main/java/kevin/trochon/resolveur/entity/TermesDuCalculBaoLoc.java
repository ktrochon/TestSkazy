package kevin.trochon.resolveur.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "BAOLOCRESULTAT")
public class TermesDuCalculBaoLoc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    private int terme1;
    private int terme2;
    private int terme3;
    private int terme4;
    private int terme5;
    private int terme6;
    private int terme7;
    private int terme8;
    private int terme9;
    private LocalTime tempsExecution;
    private int resultat;

    public TermesDuCalculBaoLoc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerme1() {
        return terme1;
    }

    public void setTerme1(int terme1) {
        this.terme1 = terme1;
    }

    public int getTerme2() {
        return terme2;
    }

    public void setTerme2(int terme2) {
        this.terme2 = terme2;
    }

    public int getTerme3() {
        return terme3;
    }

    public void setTerme3(int terme3) {
        this.terme3 = terme3;
    }

    public int getTerme4() {
        return terme4;
    }

    public void setTerme4(int terme4) {
        this.terme4 = terme4;
    }

    public int getTerme5() {
        return terme5;
    }

    public void setTerme5(int terme5) {
        this.terme5 = terme5;
    }

    public int getTerme6() {
        return terme6;
    }

    public void setTerme6(int terme6) {
        this.terme6 = terme6;
    }

    public int getTerme7() {
        return terme7;
    }

    public void setTerme7(int terme7) {
        this.terme7 = terme7;
    }

    public int getTerme8() {
        return terme8;
    }

    public void setTerme8(int terme8) {
        this.terme8 = terme8;
    }

    public int getTerme9() {
        return terme9;
    }

    public void setTerme9(int terme9) {
        this.terme9 = terme9;
    }
    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public LocalTime getTempsExecution() {
        return tempsExecution;
    }

    public void setTempsExecution(LocalTime tempsExecution) {
        this.tempsExecution = tempsExecution;
    }

    @Override
    public String toString() {
        return "TermesDuCalculBaoLoc{" +
                "id=" + id +
                ", terme1=" + terme1 +
                ", terme2=" + terme2 +
                ", terme3=" + terme3 +
                ", terme4=" + terme4 +
                ", terme5=" + terme5 +
                ", terme6=" + terme6 +
                ", terme7=" + terme7 +
                ", terme8=" + terme8 +
                ", terme9=" + terme9 +
                ", tempsExecution=" + tempsExecution +
                ", resultat=" + resultat +
                '}';
    }
}
