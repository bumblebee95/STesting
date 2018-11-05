package de.hsrm.aufgabe2;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToDoList extends ArrayList<ToDo> {

    public int getOpenEntries() {
        int nrs = 0;
        for (ToDo todo: this){
            if (todo.getStatus().equals(Status.OFFEN)){
                nrs++;
            }
        }
        return nrs;
    }

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

    public boolean save(String fileName){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            for (ToDo todo: this){
                br.write(todo.getId()+ ",");
                br.write(todo.getBez() + ",");
                br.write(todo.getStatus().toString());
                br.newLine();
            }
            br.close();
            return true;
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        return false;
    }

    public boolean load(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.ready()){
                String l = br.readLine();
                String[] line = l.split(",");
                if (line.length != 3){
                    Logger.getLogger("ToDoLogger").log(Level.WARNING, "Ungültige Zeile: " + l);
                }
                else {
                    ToDo todo = new ToDo(line[1]);
                    todo.setStatus(Status.valueOf(line[2]));
                    todo.setId(Integer.parseInt(line[0]));
                    this.add(todo);
                }
            }
            br.close();
            return true;
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        return false;
    }
}
