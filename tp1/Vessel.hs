module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cnatida de bahias, la altura de las mismas y una ruta
newV cantStack capacity ruta= (Ves (replicate cantStack (newS capacity)) ruta)

freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves stacks route) = sum (map freeCellsS stacks)

loadStacks :: [Stack] -> Route ->Container -> [Stack] -- carga un contenedor en una pila
loadStacks (x:xs) route cont | holdsS x cont route = stackS x cont : xs
                             | otherwise = x : loadStacks xs route cont

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
-- usar freeCellsV, stackS, holdsS
loadV (Ves (x:xs) route) cont = (Ves (loadStacks (x:xs) route cont) route)

unloadStack :: [Stack] -> String -> [Stack]  -- responde una pila al que se le han descargado los contenedores que podían descargarse en la ciudad
unloadStack (x:xs) city | length (x:xs) == 1 = (popS x city:xs)
                        | otherwise= popS x city : unloadStack xs city

unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
unloadV (Ves (x:xs) route) city = (Ves (unloadStack (x:xs) city) route)

netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves stacks route) = sum (map netS stacks)

closure :: [Stack] -> Container -> Route -> Bool
closure [] cont route = False
closure (x:xs) cont route | (freeCellsS (x) > 0) && (holdsS x cont route) = True
                          | otherwise = closure xs cont route
