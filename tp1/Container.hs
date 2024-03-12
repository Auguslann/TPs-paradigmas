module Container ( Container, newC, destinationC, netC )
 where

data Container = Con String Int deriving (Eq, Show)

newC :: String -> Int -> Container   -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
newC city weight = (Con city weight)

destinationC :: Container -> String  -- responde la ciuda destino del contenedor
destinationC (Con city weight) = city

netC :: Container -> Int             -- responde el peso en toneladas del contenedor
netC (Con city weight) = weight
