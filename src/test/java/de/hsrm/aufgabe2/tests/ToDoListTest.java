package de.hsrm.aufgabe2.tests;

import de.hsrm.aufgabe2.Status;
import de.hsrm.aufgabe2.ToDo;
import de.hsrm.aufgabe2.ToDoList;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

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
        todo3.setStatus(Status.IN_ARBEIT);
        todo3.setStatus(Status.BEENDET);
        assertEquals(2, todoList.getOpenEntries());
        todoList.remove(todo3);
        assertEquals(2, todoList.getOpenEntries());
        todoList.remove(todo1);
        assertEquals(1, todoList.getOpenEntries());
    }

    /**
     * Teste das Entfernen eines Elements, welches in der Liste existiert.
     */
    @Test
    void testRemoveExistingElement(){
        ToDoList list = new ToDoList();
        ToDo t = new ToDo("lala");
        list.add(t);
        assertTrue(list.remove(t));
    }

    /**
     * Teste das Entfernen eines Elements, welches !nicht! in der Liste existiert.
     */
    @Test
    void testRemoveNotExistingElement(){
        ToDoList list = new ToDoList();
        ToDo t = new ToDo("lala");
        assertFalse(list.remove(t));
    }

    /**
     * IN einer leeren Liste müssen genau 0 offene Todos sein
     */
    @Test
    void testGetOpenToDosOfEmptyList() {
        assertEquals(0, new ToDoList().getOpenEntries());
    }

    /**
     * Leere Liste muss die größe 0 haben
     */
    @Test
    void testEmptyListSizeNull(){
        assertEquals(0,new ToDoList().size());
    }

    /**
     * Test auf zwei Todos, die inhaltlich gleich sind
     */
    @Test
    void twoEntryAreSame(){
        ToDo t1 = new ToDo("1");
        t1.setInhalt("Inhalt");
        ToDo t2 = new ToDo("1");
        t2.setInhalt("Inhalt");
        ToDoList list = new ToDoList();
        list.add(t1);
        list.add(t2);

        assertEquals(0, list.get(0).compareTo(list.get(1)));
    }

    /**
     * Test auf zwei Todos, die inhaltlich nicht gleich sind
     */
    @Test
    void twoEntryAreNotSame(){
        ToDo t1 = new ToDo("1");
        t1.setInhalt("Inhalt");
        ToDo t2 = new ToDo("2");
        t2.setInhalt("Inhalt");
        ToDoList list = new ToDoList();
        list.add(t1);
        list.add(t2);

        assertEquals(-1, list.get(0).compareTo(list.get(1)));
    }

    /**
     * Test ob man den Status von Offen auf IN_ARBEIT ändern kann
     */
    @Test
    void testStatusOffenToInArbeit(){
        ToDo t1 = new ToDo("1");
        t1.setStatus(Status.IN_ARBEIT);
        assertEquals(Status.IN_ARBEIT,t1.getStatus());
    }

    /**
     * Test darauf, dass ein Wechsel von Beendet nach Offen fehlschlägt
     */
    @Test
    void testStatusBeendetToOffen(){
        ToDo t1 = new ToDo("1");
        t1.setStatus(Status.IN_ARBEIT);
        t1.setStatus(Status.BEENDET);
        t1.setStatus(Status.OFFEN);
        assertNotEquals(Status.OFFEN,t1.getStatus());
    }

    /**
     * Teste eine Liste auf das Entfernen aller Elemente
     */
    @Test
    void testDeleteAllEntries() {
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

        //Entferne alle Elemente mittels Iterator
        Iterator it = todoList.iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }

        assertEquals(0, todoList.size());
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

    /**
     * Testen des Speicherns einer leeren Liste.
     */
    @Test
    void testTrySaveEmptyList(){
        ToDoList list = new ToDoList();
        assertFalse(list.save("wirdEhNichtGespeichert.csv"));
    }

    /**
     * Testen auf das Sortieren einer Liste.
     * Wird mittels compareTo sortiert, also nach Status, dann Titel....
     */
    @Test
    void testGetSortedList(){
        ToDoList todoList = new ToDoList();
        ToDo todo1 = new ToDo("Todo1");
        todo1.setInhalt("Dies ist ein Test");

        todoList.add(new ToDo(("Todo2")));
        ToDo todo3 = new ToDo("Todo3");
        todo3.setInhalt("3+3=6");
        todo3.setStatus(Status.IN_ARBEIT);
        todoList.add(new ToDo(("Todo4")));
        todoList.add(todo3);
        ToDo todo4 = new ToDo("Trala");
        todo4.setStatus(Status.IN_ARBEIT);
        todo4.setStatus(Status.BEENDET);
        todo4.setInhalt("ab");
        ToDo todo5 = new ToDo("Trala");
        todo5.setInhalt("aa");
        todo5.setStatus(Status.IN_ARBEIT);
        todo5.setStatus(Status.BEENDET);
        todoList.add(todo5);
        todoList.add(todo4);
        todoList.add(todo1);

        todoList = todoList.getSortedList();

        assertEquals(Status.OFFEN, todoList.get(0).getStatus());
        assertEquals(Status.OFFEN, todoList.get(1).getStatus());
        assertEquals(Status.OFFEN, todoList.get(2).getStatus());
        assertEquals(Status.IN_ARBEIT, todoList.get(3).getStatus());
        assertEquals(Status.BEENDET, todoList.get(4).getStatus());
        assertEquals(Status.BEENDET, todoList.get(5).getStatus());

        assertEquals("Trala", todoList.get(5).getBez());
        assertEquals("Todo3", todoList.get(3).getBez());

        assertEquals("aa", todoList.get(4).getInhalt());
        assertEquals("ab", todoList.get(5).getInhalt());
    }

    /**
     * Test des Filterns nach Status.
     */
    @Test
    void testFilterList(){
        ToDoList todoList = new ToDoList();
        ToDo todo1 = new ToDo("Todo1");
        todo1.setInhalt("Dies ist ein Test");

        todoList.add(new ToDo(("Todo2")));
        ToDo todo3 = new ToDo("Todo3");
        todo3.setInhalt("3+3=6");
        todo3.setStatus(Status.IN_ARBEIT);
        todoList.add(new ToDo(("Todo4")));
        todoList.add(todo3);
        ToDo todo4 = new ToDo("Trala");
        todo4.setStatus(Status.IN_ARBEIT);
        todo4.setStatus(Status.BEENDET);
        todo4.setInhalt("ab");
        ToDo todo5 = new ToDo("Trala");
        todo5.setInhalt("aa");
        todo5.setStatus(Status.IN_ARBEIT);
        todo5.setStatus(Status.BEENDET);
        todoList.add(todo5);
        todoList.add(todo4);
        todoList.add(todo1);

        ToDoList open = todoList.getStatusFilteredList(Status.OFFEN);
        assertEquals(3, open.size());

        ToDoList inwork = todoList.getStatusFilteredList(Status.IN_ARBEIT);
        assertEquals(1, inwork.size());

        ToDoList beendet = todoList.getStatusFilteredList(Status.BEENDET);
        assertEquals(2, beendet.size());
    }
}