package de.hsrm.aufgabe2.tests;

import de.hsrm.aufgabe2.Status;
import de.hsrm.aufgabe2.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testen der Klasse ToD0
 */
class ToDoTest {

    /**
     * Testen des Status eines ToDos.
     */
    @Test
    void testToDoStatus() {
        String bez = "First todo is to do a todo";
        ToDo todo = new ToDo(bez);
        assertEquals(Status.OFFEN, todo.getStatus());
        todo.setStatus(Status.IN_ARBEIT);
        assertEquals(Status.IN_ARBEIT, todo.getStatus());
        todo.setStatus(Status.BEENDET);
        assertEquals(Status.BEENDET, todo.getStatus());
        //nach offen geht nicht mehr, deswegen muss auf beendet bleiben
        todo.setStatus(Status.OFFEN);
        assertEquals(Status.BEENDET, todo.getStatus());
    }

    /**
     * Testen der Bezeichnung eines ToDos
     */
    @Test
    void testToDoBez(){
        String bez = "gummibärenbande";
        ToDo todo = new ToDo(bez);
        assertEquals(bez, todo.getBez());
        todo.setBez("Neuer Titel");
        assertEquals("Neuer Titel", todo.getBez());
    }

    @Test
    void testHash(){
        ToDo todo = new ToDo("test2");
        assertEquals(todo.hashCode(), todo.getId());
        todo.setId(56321421);
        assertNotEquals(todo.hashCode(), todo.getId());
        todo.setId(345623458);
        assertEquals(todo.hashCode(), todo.getId());
    }
}