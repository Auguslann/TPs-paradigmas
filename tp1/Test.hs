import Vessel
import Route
import Stack
import Container


cont1 = newC "Buenos Aires" 10
cont2 = newC "Rosario" 20

stack1 = newS 10

route1 = newR ["Buenos Aires", "Rosario", "Cordoba"]

vessel1 = newV 2 10 route1
