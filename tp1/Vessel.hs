module Vessel ( Vessel, newV, freeCellsV, loadV){-, unloadV, netV )-}
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cnatida de bahias, la altura de las mismas y una ruta
newV cantStack capacity ruta= (Ves (replicate cantStack (newS capacity)) ruta)

freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves stacks route) = sum (map freeCellsS stacks)


getStack :: Vessel -> Int -> Stack
getStack (Ves stacks route) n = stacks !! n

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
-- usar freeCellsV, stackS, holdsS
loadV (Ves stackslist route) cont | (freeCellsV (Ves stackslist route) >= 0) && (holdsS (head stackslist) cont route) = (Ves ([stackS (head stackslist) cont] ++ tail stackslist) route)
                               | otherwise = error "No se puede cargar el contenedor: Barco lleno o no cumple con la ruta"

{-
unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
-- usar destinationC, popS

netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
-- usar map y netS
-}
