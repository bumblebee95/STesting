
import de.hsrm.aufgabe2.Status;
import de.hsrm.aufgabe2.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToDoStatus() {
        String bez = "First todo is to do a todo";
        ToDo todo = new ToDo(bez);
        assertEquals(Status.OFFEN, todo.getStatus());
        todo.setStatus(Status.IN_ARBEIT);
        assertEquals(Status.IN_ARBEIT, todo.getStatus());
        todo.setStatus(Status.BEENDET);
        assertEquals(Status.BEENDET, todo.getStatus());
    }

    @Test
    void testToDoBez(){
        String bez = "gummibärenbande";
        ToDo todo = new ToDo(bez);
        assertEquals(bez, todo.getBez());
    }

    @Test
    void testHash(){
        ToDo todo = new ToDo("test2");
        assertEquals(todo.hashCode(), todo.getId());
    }
}