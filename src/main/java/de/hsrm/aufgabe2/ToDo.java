package de.hsrm.aufgabe2;

public class ToDo {

    private String bez;
    private Status status;
    private int id;

    public ToDo(String bez){
        if (bez == null || bez.trim().isEmpty()){
            System.err.println("Bezeichnung cannot be null or empty!");
        }
        this.bez = bez;
        status = Status.OFFEN;
        id = this.hashCode();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBez() {
        return bez;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        if (id == 0){
            return super.hashCode();
        }
        return id;
    }
}
