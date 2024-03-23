import Container
import Stack
import Vessel
import Route

import Control.Exception
import System.IO.Unsafe

cont2 = newC "Buenos Aires" 15
cont1 = newC "Rosario" 5
cont0 = newC "Cordoba" 5
cont3 = newC "Cordoba" 25
cont4 = newC "Belgrano" 5

stack1 = newS 10

route1 = newR ["Buenos Aires", "Rosario", "Cordoba"]

vessel0 = newV 2 1 route1
vessel1 = loadV vessel0 cont0
vessel2 = loadV vessel1 cont1
vessel3 = loadV vessel2 cont2
vessel4 = loadV vessel0 cont3
vessel5 = loadV vessel0 cont4




t =[
    destinationC(cont0) == "Cordoba", -- "C1 destino de un contenedor"
    inOrderR(route1) "Buenos Aires" "Rosario", -- "R1 enOrden"
    inOrderR(route1) "Buenos Aires" "Cordoba", -- "R2 enOrden"
    inOrderR(route1) "Rosario" "Cordoba", -- "R3 enOrden"
    not (inOrderR(route1) "Cordoba" "Buenos Aires"), -- "R4 enOrden"
    freeCellsV(vessel3) == 0, -- "Vessel 3 no carga cont 2"
    testF(freeCellsV(vessel4) == 0), -- "Vessel 4 no carga cont 3"
    netV(vessel0) == netV(vessel5), -- "Vessel 5 no carga cont 0 porque su destino no esta en la ruta"
    testF(newV (-1) 1 route1), -- "Vessel no acepta cantidades negativas"
    True]


testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()
