import Vessel
import Route
import Stack
import Container


cont2 = newC "Buenos Aires" 10
cont1 = newC "Rosario" 20
cont0 = newC "Cordoba" 20
cont3 = newC "Rosario" 5
cont4 = newC "Cordoba" 5
cont5 = newC "Buenos Aires" 10
cont6 = newC "Buenos Aires" 10
cont7 = newC "Rosario" 1
cont8 = newC "Cordoba" 1
cont9 = newC "Buenos Aires" 1


stack1 = newS 10

route1 = newR ["Buenos Aires", "Rosario", "Cordoba"]

vessel0 = newV 2 1 route1
vessel1 = loadV vessel0 cont0
vessel2 = loadV vessel1 cont1
vessel3 = loadV vessel2 cont2
vessel4 = unloadV vessel3 "Rosario"
vessel5 = unloadV vessel4 "Cordoba"
vessel6 = unloadV vessel5 "Buenos Aires"
vessel7 = loadV vessel6 cont6
vessel8 = loadV vessel7 cont7
vessel9 = loadV vessel8 cont8

vessel10 = foldr (flip loadV) vessel0 [cont0, cont1, cont2, cont3, cont4, cont5, cont6, cont7, cont8, cont9]
