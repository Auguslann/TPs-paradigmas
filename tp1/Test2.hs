import Vessel
import Route
import Stack
import Container


cont2 = newC "Buenos Aires" 10
cont1 = newC "Rosario" 5
cont0 = newC "Cordoba" 5
cont3 = newC "Rosario" 5
cont4 = newC "Cordoba" 5
cont5 = newC "Buenos Aires" 10

stack1 = newS 10

route1 = newR ["Buenos Aires", "Rosario", "Cordoba"]

vessel0 = newV 3 1 route1
vessel1 = loadV vessel0 cont0
vessel2 = loadV vessel1 cont1
vessel3 = loadV vessel2 cont2
vessel4 = unloadV vessel3 "Rosario"
vessel5 = unloadV vessel4 "Cordoba"
vessel6 = unloadV vessel5 "Buenos Aires"
