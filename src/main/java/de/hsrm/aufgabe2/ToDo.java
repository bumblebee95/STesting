package de.hsrm.aufgabe2;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tod0 ist ein Eintrag innerhalb einer ToDoList
 */
public class ToDo implements Comparable<ToDo> {

    /** Die Bezeichnung des ToDos - der Titel. */
    private String bez;
    /** Der aktuelle Status des ToDos. */
    private Status status;
    /** Die ID des ToDos. */
    private int id;
    /** Der Text des ToDos. */
    private String inhalt;

    /**
     * Erzeugt ein neues ToD0, mit dem Status "Offen".
     * Die ID wird aus dem Hash des Objekts generiert.
     * @param bez Bezeichnung - Titel des ToDos
     */
    public ToDo(String bez){
        if (bez == null || bez.trim().isEmpty()){
            Logger.getLogger("ToDoLogger").log(Level.WARNING, "Bezeichnung cannot be null");
            //setzte bezeichnung auf leer
            bez = "";
        }
        this.bez = bez;
        status = Status.OFFEN;
        id = this.hashCode();
        inhalt = "";
    }

    /**
     * Setzt den Text des ToDos
     * @param inhalt text
     */
    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    /**
     * @return den Inhalt - Text des ToDos
     */
    public String getInhalt() {
        return inhalt;
    }

    /**
     * Setzt die ID des ToDos
     * @param id id des ToDos
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return die ID des ToDos
     */
    public int getId() {
        return id;
    }

    /**
     * @return den Titel des ToDos
     */
    public String getBez() {
        return bez;
    }

    /**
     * Setzt einen neuen Titel
     * @param bez titel
     */
    public void setBez(String bez) {
        this.bez = bez;
    }

    /**
     * @return den Status des ToDos
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Setzt den Status des ToDos
     * @param status status anhand enum
     */
    public void setStatus(Status status) {
        if (this.getStatus().canSwitchTo(status)) {
            this.status = status;
        }
    }

    /**
     * Wird benötigt um die ID eines ToDos zu generieren.
     * Eine ID muss mindestens 9 Stellen haben, ansonsten wird diese neu generiert!
     * @return id
     */
    @Override
    public int hashCode() {
        if (String.valueOf(id).length() <= 8){
            return super.hashCode();
        }
        return id;
    }

    /**
     * Vergleicht nach folgendem Schema:
     *      - Zuerst ordnen nach Status, falls dieser identisch ist
     *      - vergleich und sortierung nach bezeichnung(Titel), falls auch dieser identisch ist
     *      - vergleich und sortierung nach Inhalt
     *
     * @param o zu vergleichendes objekt (todo)
     * @return 1 wenn o vor this und -1 wenn o nach this
     */
    @Override
    public int compareTo(ToDo o) {
        int res = -1;
        //safety check
        if (o instanceof ToDo) {
            if (o.getStatus() == this.getStatus()) {
                if (this.getBez().equals(o.getBez())){
                    res = this.getInhalt().compareToIgnoreCase(o.getInhalt());
                }
                else {
                    res = this.getBez().compareToIgnoreCase(o.getBez());
                }
            }
            else if ((o.getStatus() == Status.OFFEN) || (o.getStatus() == Status.IN_ARBEIT && this.getStatus() == Status.BEENDET) ){
                res = 1;
            }
            else if ((this.getStatus() == Status.OFFEN) || (this.getStatus() == Status.IN_ARBEIT && o.getStatus() == Status.BEENDET) ){
                res = -1;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Id: " + this.getId() + " \n");
        buf.append("Titel: " + this.getBez() + " \n");
        buf.append("Status: " + this.getStatus() + " \n");
        buf.append("Inhalt: " + this.getInhalt() + " \n");
        return buf.toString();
    }
}
