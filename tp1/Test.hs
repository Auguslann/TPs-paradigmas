import Container
import Stack
import Vessel
import Route

import Control.Exception
import System.IO.Unsafe

mdq = "MDQ"
rsl = "RSL"
bhi = "BHI"
bue = "BUE"
qeq = "QEQ"
mvd = "MVD"

rutaLarga = newR [ bhi, qeq, mdq, bue, rsl ]

bS = newV 1 1 rutaLarga
bChato = newV 2 1 rutaLarga
bChatoX = loadV bChato cMdq
bAlto = newV 1 2 rutaLarga
bAltoX = loadV bAlto cMdq

cMdq = newC mdq 5
cBue = newC bue 7
cQeq = newC qeq 9

sLL = newS 2
sXL = stackS sLL cMdq
sXX = stackS sXL cMdq

t = [ destinationC cMdq == "MDQ", -- "C1 destino de un contenedor"
      inOrderR rutaLarga bhi qeq, -- "R1 enOrden"
      inOrderR rutaLarga bhi rsl, -- "R2 enOrden"
      inOrderR rutaLarga qeq mdq, -- "R3 enOrden"
      inOrderR rutaLarga qeq rsl, -- "R4 enOrden"
      testF  (stackS sXX cMdq), -- "S4 una pila no tolera mas que su capacidad"
      True ]

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()
