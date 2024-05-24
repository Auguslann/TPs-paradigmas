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
    }


    @Test
    public void test2() {
        Axiom dron = new Axiom();
        dron.Orders('l');
        speedAndDirection(dron,0,"Oeste");
        assertEquals("Sonda desplegada", dron.Orders('i').Orders('i').Orders('d').getSonda());
        dron.Orders('f').Orders('r');
        speedAndDirection(dron,2,"Norte");
    }
    @Test public void test4(){
        assertEquals("Norte",new Axiom().Orders('i').Orders('r').Orders('l').getDirection());
    }
    @Test public void test05(){
        assertEquals(0 ,new Axiom().Orders('s').Speed() );
    }
    @Test public void test06(){
        Axiom boat = new Axiom();
        assertThrowsLike( "Can't deploy it it's not moving", () -> boat.Orders('d'));
    }
    @Test public void test07(){
        Axiom boat = new Axiom();
        assertEquals(0,boat.Orders('i').Orders('s').Speed());
    }
    @Test public void test08(){
        Axiom dorn = new Axiom();
        assertEquals("Sonda recuperada",dorn.Orders('i').Orders('d').Orders('f').getSonda());
    }
    @Test public void test09(){
        assertThrowsLike("Can't turn with the probe deployed",() ->new Axiom().Orders('i').Orders('d').Orders('r'));
    }
    @Test public void test10(){
        assertThrowsLike("Can't decrease speed with the probe deployed",() ->new Axiom().Orders('i').Orders('d').Orders('s'));
    }
    private static void speedAndDirection(Axiom dron, int expected_speed, String expected_direction) {
        assertEquals(expected_speed, dron.Speed());
        assertEquals(expected_direction, dron.getDirection());
    }

    private void assertThrowsLike(String message, Executable codeBlock) {
        assertEquals(message, assertThrows(Exception.class, codeBlock).getMessage());
    }
}
