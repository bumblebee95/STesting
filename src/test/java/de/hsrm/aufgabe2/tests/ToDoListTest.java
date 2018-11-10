package de.hsrm.aufgabe2.tests;

import de.hsrm.aufgabe2.Status;
import de.hsrm.aufgabe2.ToDo;
import de.hsrm.aufgabe2.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {


    /**
     * Test auf offene Todos.
     */
    @Test
    void testGetOpenToDos() {
        ToDoList todoList = new ToDoList();
        ToDo todo1 = new ToDo("Todo1");
        todoList.add(todo1);
        todoList.add(new ToDo(("Todo2")));
        assertEquals(2, todoList.getOpenEntries());

        ToDo todo3 = new ToDo("ToDo3");
        todoList.add(todo3);
        assertEquals(3, todoList.getOpenEntries());
        todo3.setStatus(Status.BEENDET);
        assertEquals(2, todoList.getOpenEntries());
        todoList.remove(todo3);
        assertEquals(2, todoList.getOpenEntries());
        todoList.remove(todo1);
        assertEquals(1, todoList.getOpenEntries());
    }

    /**
     * Test der Methoden save und load.
     */
    @Test
    void testSaveAndOpen() {
        ToDoList todoList = new ToDoList();
        ToDo todo1 = new ToDo("Todo1");
        todo1.setInhalt("Dies ist ein Test");
        todoList.add(todo1);
        todo1.setStatus(Status.BEENDET);
        todoList.add(new ToDo(("Todo2")));
        ToDo todo3 = new ToDo("Todo3");
        todo3.setInhalt("3+3=6");
        todo3.setStatus(Status.IN_ARBEIT);
        todoList.add(todo3);

        //speichere im proj verzeichnis
        assertTrue(todoList.save("output/aufgabe2_out.csv"));

        //lade eben jene datei wieder ein
        ToDoList loadedList = new ToDoList();
        assertTrue(loadedList.load("output/aufgabe2_out.csv"));

        //vergleiche die zwei Listen - müssen identisch sein!!
        for (int i = 0; i < todoList.size(); i++){
            assertEquals(todoList.get(i).getStatus(), loadedList.get(i).getStatus());
            assertEquals(todoList.get(i).getId(), loadedList.get(i).getId());
            assertEquals(todoList.get(i).getBez(), loadedList.get(i).getBez());
            assertEquals(todoList.get(i).getInhalt(), loadedList.get(i).getInhalt());
        }

        //save false, because exception
        assertFalse(todoList.save(null));
        //load false, because exception
        assertFalse(todoList.load(null));

    }


}