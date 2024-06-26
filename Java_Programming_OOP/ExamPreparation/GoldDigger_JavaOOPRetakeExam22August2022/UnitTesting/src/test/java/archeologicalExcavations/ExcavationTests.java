package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.UnitTesting.src.test.java.archeologicalExcavations;

import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.UnitTesting.src.main.java.archeologicalExcavations.Archaeologist;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.UnitTesting.src.main.java.archeologicalExcavations.Excavation;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ExcavationTests {
    @Test(expected=NullPointerException.class)
    public void testCreateExcavationExceptionWhenNullName() {
        new Excavation(null, 10);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateExcavationExceptionWhenEmptyName() {
        new Excavation("     ", 10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateExcavationExceptionWhenNegativeCapacity() {
        new Excavation("name", -10);
    }

    @Test
    public void testCreateExcavationWithZeroCapacity() {
        new Excavation("name", 0);
    }

    @Test
    public void testCreateExcavationWithValidNameAndPositiveCapacity() {
        new Excavation("name", 10);
    }

    @Test
    public void testGetName() {
        Excavation validName = new Excavation("name", 10);

        assertEquals("name", validName.getName());
    }

    @Test
    public void testGetCapacity() {
        Excavation validName = new Excavation("name", 10);

        assertEquals(10, validName.getCapacity());
    }

    @Test
    public void testGetCountIsZeroOnEmptyExcavation() {
        Excavation validCount = new Excavation("name", 10);

        assertEquals(0, validCount.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddArcheologistsWithSameName() {
        Excavation valid = new Excavation("valid", 10);
        Archaeologist archaeologist = new Archaeologist("name", 10);

        valid.addArchaeologist(archaeologist);
        valid.addArchaeologist(archaeologist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddArcheologistsWithFullCapacity() {
        Excavation valid = new Excavation("valid", 2);
        Archaeologist archaeologist1 = new Archaeologist("name1", 10);
        Archaeologist archaeologist2 = new Archaeologist("name2", 10);
        Archaeologist archaeologist3 = new Archaeologist("name3", 10);

        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        valid.addArchaeologist(archaeologist3);
    }

    @Test
    public void testAddArcheologistsIncreasesCount() {
        Excavation valid = new Excavation("valid", 10);
        Archaeologist archaeologist1 = new Archaeologist("name1", 10);
        Archaeologist archaeologist2 = new Archaeologist("name2", 10);
        Archaeologist archaeologist3 = new Archaeologist("name3", 10);

        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        valid.addArchaeologist(archaeologist3);

        assertEquals(3, valid.getCount());
    }

    @Test
    public void removeArcheologistsReturnsFalseForMissingName() {
        Excavation valid = new Excavation("valid", 10);
        Archaeologist archaeologist1 = new Archaeologist("name1", 10);
        valid.addArchaeologist(archaeologist1);

        boolean result = valid.removeArchaeologist("name2");

        assertFalse(result);
        assertEquals(1, valid.getCount());
    }

    @Test
    public void testRemoveArcheologistsDecreasesCount() {
        Excavation valid = new Excavation("valid", 10);
        Archaeologist archaeologist1 = new Archaeologist("name1", 10);
        Archaeologist archaeologist2 = new Archaeologist("name2", 10);
        Archaeologist archaeologist3 = new Archaeologist("name3", 10);

        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        valid.addArchaeologist(archaeologist3);

        boolean result = valid.removeArchaeologist(archaeologist1.getName());

        assertTrue(result);
        assertEquals(2, valid.getCount());
    }

    @Test
    public void testRemoveArcheologistOnEmptyExcavationWillNotThrow() {
        Excavation valid = new Excavation("valid", 10);

        boolean result = valid.removeArchaeologist("name2");
        assertFalse(result);
    }
}
