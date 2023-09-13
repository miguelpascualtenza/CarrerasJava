# README
### fet per Kevin Simon i Miguel Pascual

Aquest programa en Java és un joc de carreres que permet als usuaris afegir jugadors, mostrar-los i eliminar-los i jugar carreres entre ells.

![Picachu corrent](https://user-images.githubusercontent.com/98650471/221304749-b348fad5-cbfd-43d0-ac33-e0cf023ac304.gif)

## Com funciona

El programa utilitza l'entrada de la consola per interactuar amb els usuaris. L'usuari pot triar una de les següents opcions en el menú:

- Afegir un nou jugador: l'usuari pot afegir un nou jugador a la llista de jugadors.
- Mostrar jugadors: l'usuari pot veure la llista de tots els jugadors i les seves puntuacions.
- Eliminar jugadors: l'usuari pot eliminar un jugador de la llista de jugadors.
- Executar el joc: l'usuari pot començar una carrera entre tots els jugadors.
- Sortir: l'usuari pot sortir del programa.

Per jugar una carrera, el programa crea una matriu en la qual cada jugador és representat per un emoji d'una sabata. Cada jugador mou la seva sabata un nombre aleatori de caselles en cada torn. El primer jugador a arribar a la meta guanya la carrera. El programa també actualitza la puntuació del guanyador de la carrera.

## Funcions

El codi inclou diverses funcions que permeten al programa interactuar amb els usuaris i actualitzar les dades de la llista de jugadors. Aquestes funcions inclouen:

- `isInt`: verifica si l'entrada de l'usuari és un nombre enter dins d'un rang especificat.
- `addString`: afegeix una nova cadena a una llista.
- `addInteger`:  afegeix un nou nombre enter a una llista.
- `runMenu`: mostra el menú i crida la funció corresponent segons l'opció triada per l'usuari.
- `demanaIndex`: sol·licita a l'usuari l'índex del jugador que vol eliminar de la llista.
- `eliminarNomJugador`: elimina el jugador seleccionat de la llista de jugadors.
- `eliminarPuntuacioJugador`: elimina la puntuació del jugador seleccionat de la llista de puntuacions.
- `mostrarjugadores`: mostra la llista de tots els jugadors i les seves puntuacions.
- `inicioJuego`: crea i executa una carrera entre tots els jugadors. El guanyador de la carrera es determina i s'actualitza la seva puntuació a la llista de jugadors.

## Emoji utilitzats

En la matriu de carreres s'utilitzen els següents emojis per representar els elements del joc:

- 👟: representa a cada jugador.
- 🧱: representa les caselles buides a la matriu.
- 🏁: representa la casella de la meta.

![finish](https://user-images.githubusercontent.com/98650471/221305084-ff049e70-9970-41b5-be9d-6907510eadc2.gif)
