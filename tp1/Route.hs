module Route ( Route, newR, inOrderR )
 where

import Data.List (elemIndex)

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR cities = (Rou cities)


routeToList :: Route -> [ String ]            -- convierte una ruta en una lista de ciudades
routeToList (Rou cities) = cities

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta
inOrderR route city1 city2 = (elem city1 (routeToList route)) && (elem city2 (routeToList route)) && (elemIndex city1 (routeToList route) <= elemIndex city2 (routeToList route))
