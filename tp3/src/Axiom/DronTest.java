package Axiom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class DronTest {
    @Test
    public void testInitialSpeedAndDirection() {
        Axiom dron = new Axiom();
        speedAndDirection(dron, 0, "Norte");
    }
    @Test public void testLeftTrunAndProbeDeployment() {
        Axiom dron = new Axiom();
        speedAndDirection(dron.Orders('l'),0,"Oeste");
        assertEquals("Probe deployed", dron.Orders('i').Orders('i').Orders('d').getSonda());
        dron.Orders('f').Orders('r');
        speedAndDirection(dron,2,"Norte");
    }
    @Test public void testLeftAndRightTurns(){
        assertEquals("Norte",new Axiom().Orders('i').Orders('r').Orders('l').getDirection());
    }
    @Test public void testDoesNotDecreaseSpeed(){
        assertEquals(0 ,new Axiom().Orders('s').Speed() );
    }
    @Test public void testCanNotDeployWhenNotMoving(){
        Axiom boat = new Axiom();
        assertThrowsLike( "Can't deploy it it's not moving", () -> boat.Orders('d'));
    }
    @Test public void testDecreaseSeepTo0(){
        Axiom boat = new Axiom();
        assertEquals(0,boat.Orders('i').Orders('s').Speed());
    }
    @Test public void testProbeNotDeployed(){
        assertEquals("Probe not deployed",new Axiom().Orders('f').getSonda());
    }
    @Test public void testDeploymentAndRecovered(){
        Axiom dorn = new Axiom();
        assertEquals("Probe not deployed",dorn.Orders('i').Orders('d').Orders('f').getSonda());
    }
    @Test public void testCantTurnRightWithProbeDeployed(){
        assertThrowsLike("Can't turn with the probe deployed",() ->new Axiom().Orders('i').Orders('d').Orders('r'));
    }
    @Test public void testCantTurnLeftWithProbeDeployed(){
        assertThrowsLike("Can't turn with the probe deployed",() ->new Axiom().Orders('i').Orders('d').Orders('l'));
    }
    @Test public void testCantStopWithProbeDeployed(){
        assertThrowsLike("Can't stop while it's deployed",() ->new Axiom().Orders('i').Orders('d').Orders('s'));
    }
    private static void speedAndDirection(Axiom dron, int expected_speed, String expected_direction) {
        assertEquals(expected_speed, dron.Speed());
        assertEquals(expected_direction, dron.getDirection());
    }

    private void assertThrowsLike(String message, Executable codeBlock) {
        assertEquals(message, assertThrows(Exception.class, codeBlock).getMessage());
    }
}
