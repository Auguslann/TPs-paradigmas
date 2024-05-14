package Axiom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class DronTest {
    @Test
    public void test() {
        Axiom dron = new Axiom();
        speedAndDirection(dron, 0, "Norte");
        assertNotNull(dron);
    }


    @Test
    public void test2() {
        Axiom dron = new Axiom();
        speedAndDirection(dron,0,"Norte");
        assertEquals("Sonda desplegada", dron.Orders('i').Orders('i').Orders('d').getSonda());
        speedAndDirection(dron,2,"Norte");
    }
    @Test public void test03(){
        assertEquals("Oeste",new Axiom().Orders('l').getDirection());
    }
    @Test public void test4(){
        assertEquals("Este",new Axiom().Orders('i').Orders('r').Orders('l').getDirection());
    }
    @Test public void test05(){
        assertEquals(0 ,new Axiom().Orders('s').Speed() );
    }
    @Test public void test06(){
        Axiom boat = new Axiom();
        assertThrowsLike( "Too Slow", () -> boat.Orders('d'));
    }
    @Test public void test07(){
        Axiom boat = new Axiom();
        assertEquals(0,boat.Orders('i').Orders('s').Speed());
    }
    private static void speedAndDirection(Axiom dron, int expected_speed, String expected_direction) {
        assertEquals(expected_speed, dron.Speed());
        assertEquals(expected_direction, dron.getDirection());
    }

    private void assertThrowsLike(String message, Executable codeBlock){
        assertEquals(message,assertThrows( Exception.class , codeBlock ).getMessage());
    }
}
