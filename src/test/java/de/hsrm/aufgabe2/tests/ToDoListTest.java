package de.hsrm.aufgabe2.tests;

import de.hsrm.aufgabe2.Status;
import de.hsrm.aufgabe2.ToDo;
import de.hsrm.aufgabe2.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {


    @Test
    void testGetOpenToDos(){
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


}