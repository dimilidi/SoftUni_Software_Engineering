package Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.test.rjb_lab;

import Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.main.rjb_lab.Axe;
import Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.main.rjb_lab.Dummy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AxeTest {

    private static final int ATTACK = 13;
    private static final int DURABILITY = 42;
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 100;
    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = new Axe(ATTACK, DURABILITY);
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
    }


    @Test
    public void test_CreateAxe_ShouldSetCorrectValues_ForAttackAndDurability() {
        assertEquals(ATTACK, axe.getAttackPoints());
        assertEquals(DURABILITY, axe.getDurabilityPoints());
    }

    @Test
    public void test_Attack_Removes_DurabilityPoints() {
        axe.attack(dummy);
        assertEquals(DURABILITY - 1, axe.getDurabilityPoints());
    }

    @Test
    public void test_Attack_WithBrokenWeapon_ShouldFail() {
        Axe axe = new Axe(ATTACK, 0);
        String message = assertThrows(IllegalStateException.class, () -> axe.attack(dummy))
                .getMessage();
        assertEquals("Axe is broken.", message);
    }

}