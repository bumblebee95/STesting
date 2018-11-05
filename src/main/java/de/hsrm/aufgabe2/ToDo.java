package de.hsrm.aufgabe2;

/**
 * Tod0 ist ein Eintrag innerhalb einer ToDoList
 */
public class ToDo {

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
            System.err.println("Bezeichnung cannot be null or empty!");
        }
        this.bez = bez;
        status = Status.OFFEN;
        id = this.hashCode();
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
        this.status = status;
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
}
