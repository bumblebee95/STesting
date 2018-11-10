package de.hsrm.aufgabe2;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ToDoList enthält beliebig viele ToDos und kann diese verwalten (CRUD)
 */
public class ToDoList extends ArrayList<ToDo> {

    /**
     * @return die Anzahl an ToDos, die den Status "Open" haben.
     */
    public int getOpenEntries() {
        int nrs = 0;
        for (ToDo todo: this){
            if (todo.getStatus().equals(Status.OFFEN)){
                nrs++;
            }
        }
        return nrs;
    }

    /**
     * Entfernt ein gewähltes ToD0 anhand der ID
     * @param o das zu löschende ToD0
     * @return true wenn das Objekt gelöscht wurde, false wenn nicht
     */
    @Override
    public boolean remove(Object o) {
        if (o instanceof ToDo){
            for (ToDo todo: this){
                if (todo.hashCode() == o.hashCode()){
                    return super.remove(o);
                }
            }
        }
        return false;
    }

    /**
     * Speichert die aktuelle Liste als CSV Datei.
     * @param fileName dateiname
     * @return true wenn speichern erfolgreich, false wenn nicht
     */
    public boolean save(String fileName){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            for (ToDo todo: this){
                br.write(todo.getId()+ ",");
                br.write(todo.getBez() + ",");
                br.write(todo.getInhalt() + ",");
                br.write(todo.getStatus().toString());
                br.newLine();
            }
            br.close();
            return true;
        }
        catch(Throwable ioe){
            Logger.getLogger("ToDoLogger").log(Level.SEVERE, "Error while saving file: " + fileName + " | Exception: " + ioe.getMessage());
        }
        return false;
    }

    /**
     * Liest eine Datei ein und bildet ToDos
     * @param fileName dateiname
     * @return true wenn laden erfolgreich, false wenn nicht
     */
    public boolean load(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.ready()){
                String l = br.readLine();
                String[] line = l.split(",");
                if (line.length != 4){
                    Logger.getLogger("ToDoLogger").log(Level.WARNING, "Ungültige Zeile: " + l);
                }
                else {
                    ToDo todo = new ToDo(line[1]);
                    todo.setInhalt(line[2]);
                    todo.setStatus(Status.valueOf(line[3]));
                    todo.setId(Integer.parseInt(line[0]));
                    this.add(todo);
                }
            }
            br.close();
            return true;
        }
        catch(Throwable ioe){
            Logger.getLogger("ToDoLogger").log(Level.SEVERE, "Error while loading file: " + fileName + " | Exception: " + ioe.getMessage());
        }
        return false;
    }
}
