module Stack ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada
newS capacity = (Sta [] capacity)

freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS (Sta containers capacity) = capacity - (length containers)

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta containers capacity) container = (Sta (containers ++ [container]) capacity)

netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta containers capacity) = sum (map netC containers)


holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta containers capacity) cont route | (length containers == 0) && (inOrderR route (destinationC(cont)) (destinationC(cont))) && (netC(cont) <= 20) = True
                                            | (inOrderR route (destinationC(cont)) (destinationC(last containers))) && (freeCellsS (Sta containers capacity) > 0) && (netS (Sta containers capacity) + netC(cont) <= 20) = True
                                            | otherwise = False


canPop :: Stack -> String -> Bool             -- indica si la pila tiene contenedores con destino en la ciudad indicada
canPop (Sta containers capacity) city | length containers == 0 = error "No hay contenedores o no hay contenedores con destino en la ciudad indicada"
                                      | otherwise = destinationC(last containers) == city

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada
popS (Sta containers capacity) city | canPop (Sta containers capacity) city = ( Sta (filter (\x -> destinationC(x) /= city) containers) capacity)
                                    | otherwise = (Sta containers capacity)
-- usar destinationC y filter
-- filter (\x -> destination(x) /= city containers) muestra los elementos de containers que cumplen la condicion x
