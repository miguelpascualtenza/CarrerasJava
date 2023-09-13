import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String JUGADOR = "👟";
    public static final String LIMPIAR = "🧱";
    public static final String META = "🏁";

    /**
     * L'objectiu d'aquesta funció es executar el menú principal
     * @param args
     */
    public static void main(String[] args){
        ArrayList<String> users = new ArrayList<String>();
        ArrayList<Integer> puntuations = new ArrayList<Integer>();
        runMenu( users, puntuations);
    }

    /**
     * Comprova que la funció es un número sencer
     * @param Message Missatge d'entrada
     * @param enter Numero enter que se li entra
     * @param MIN Numero minim
     * @param MAX Numero maxim
     * @return enter
     */
    private static int isInt(String Message, int enter, int MIN, int MAX){
        Scanner input = new Scanner(System.in);
        boolean isInt = false;
        do{
            System.out.println(Message);
            isInt = input.hasNextInt();
            if(isInt){
                enter = input.nextInt();
                input.nextLine();
                if(enter < MIN || enter > MAX) {
                    System.out.println("Intodueix un número dins de l'interval MIN " + MIN + " i MAX " + MAX);
                }
            }else{
                System.out.println("Intodueix un número sencer!");
                input.nextLine();
            }
        }while (!isInt || enter < MIN || enter > MAX);
        return enter;
    }

    /**
     * L'objectiu d'aqeusta funció es afeguir un usuari a la ArrayList
     * @param Message Missatge que es mostra per pantalla
     * @param input Valor a inserir a la linkedlist
     * @return input
     */

    private static ArrayList<String> addString(String Message, ArrayList<String> input){
        Scanner enter = new Scanner(System.in);
        String name;
        System.out.println(Message);
        name = enter.nextLine();
        input.add(name);
        return input;
    }

    /**
     * L'objectiu d'aquest procediment es executar el menú principal
     * @param users
     * @param puntuations
     */
    private static void runMenu(ArrayList<String> users, ArrayList<Integer> puntuations){
        int index = -1;

        int menu = llegirInt("Escull una opcio del menu: " +
                "\n 1- Agregar un nuevo jugador " +
                "\n 2- mostrar jugadores" +
                "\n 3- Eliminar jugadores " +
                "\n 4- Ejecutar el juego " +
                "\n 5- Salir" +
                "\n", 1, 5);
        switch (menu){
            case 1:
                users = addString("Introdueix el nom del jugador", users);
                puntuations.add(0);
                runMenu( users, puntuations);;
                break;

            case 2:
                mostrarjugadores(users, puntuations);
                runMenu( users, puntuations);;

                break;


            case 3:
                if (users.size() == 0){
                    System.out.println(RED + "ERROR: agrega jugadores para poder eliminarlos" + RESET);
                }else {
                    mostrarjugadores(users, puntuations);
                    index = demanaIndex(index, users);
                    users = eliminarNomJugador(index, users);
                    puntuations = eliminarPuntuacioJugador(index, puntuations);
                }
                runMenu( users, puntuations);

            case 4:
                if (users.size() < 3){
                    System.out.println(RED + "Agrega mas jugadores para empezar a jugar" + RESET);

                }else {

                    int ganador = inicioJuego(users);
                    System.out.println(GREEN + "El jugador " + users.get(ganador) + " ha ganado la carrera" + RESET);
                    int puntosGanador = puntuations.get(ganador);
                    puntuations.set(ganador, puntosGanador += 40);

                }
                runMenu( users, puntuations);
                break;

            case 5:
                System.out.println("Saliendo...");
                break;

            default:
                runMenu( users, puntuations);
                break;

        }
    }

    /**
     * Demana un index per eliminar a la linkedlist
     * @param Index
     * @param dimension
     * @return Index
     */

    private static int demanaIndex (int Index, ArrayList<String> dimension){
        Index = isInt("Introduiex el numero de jugador per eliminar a l'arrayList", Index, 1, (dimension.size()));
        Index--;
        return Index;
    }

    /**
     * Funció que s'encarrega de eliminar un jugador a la arraylist
     * @param Index
     * @param User
     * @return User
     */
    private static ArrayList<String> eliminarNomJugador (int Index, ArrayList<String> User) {
        System.out.println("S'ha eliminat el jugador "+ User.get(Index));
        User.remove(Index);
        return User;
    }

    /**
     * Funcií que s'encarrega de eliminar la puntuació d'un jugador
     * @param Index
     * @param puntuation
     * @return
     */

    private static ArrayList<Integer> eliminarPuntuacioJugador (int Index, ArrayList<Integer> puntuation) {
        System.out.println("S'ha eliminat amb la puntuació "+ puntuation.get(Index));
        puntuation.remove(Index);
        return puntuation;
    }

    /**
     * Muestra por consola la lista de jugadores y sus respectivas puntuaciones.
     * @param users la lista de nombres de los jugadores
     * @param puntuations la lista de puntuaciones de los jugadores
     */
    private static void mostrarjugadores(ArrayList<String> users, ArrayList<Integer> puntuations) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println("Jugador "+ (i+1) + ": " + users.get(i) + " con " + puntuations.get(i) + " puntos");

        }

    }

    /**
     * Inicia el joc amb la llista d'usuaris i crea una matriu del joc amb dimensions basades en el nombre d'usuaris.
     * @param users la llista de noms dels usuaris
     * @return l'index de l'usuari guanyador en la llista d'usuaris
     */
    private static int inicioJuego(ArrayList<String> users) {

        String[][] juego = new String[users.size()][40];
        limpiarMatriz(juego);
        int ganador;
        mostrarStringMatriz(juego, users);
        do {
            juego=moverJugadores(juego);
            mostrarStringMatriz(juego, users);
            ganador = comprobarGanador(juego);
            tiempo(750);
        }while (ganador == -1);

        return ganador;

    }

    /**
     * funcio que atura el programa durant un temps determinat
     * ( hem utilitzat try catch perque sino no podiem utilitzar-ho, 
     * es unicament estetic perque s'entengui millor el joc)
     * @param espera temps a espera en milisegons
     */
    public static void tiempo(int espera) {
        try {
            Thread.sleep(espera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprova la posició actual del jugador en un array de posicions de joc.
     * @param array l'array que conté les posicions de joc.
     * @return l'índex de la posició del jugador dins l'array, o 0 si no es troba el jugador.
     *
     */

    private static int comprobarPosicionJugador(String[] array) {
        int jugador = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(JUGADOR))
                return i;
        }

        return jugador;
    }

    /**
     *  Comprova si hi ha un guanyador en la matriu de posicions de joc.
     *  @param matriz la matriu que conté les posicions dels jugadors.
     *  @return l'índex del jugador guanyador, o -1 si no hi ha guanyador.
     */
    private static int comprobarGanador(String[][] matriz) {
        int ganador = -1;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][matriz[i].length-1].equalsIgnoreCase(JUGADOR)){
                ganador = i;
                break;
            }

        }
        return ganador;
    }

    /**
     * Mou els jugadors dins d'una matriu de forma aleatòria entre 0 y 5.
     * @param matriz la matriu que conté les posicions dels jugadors.
     * @return una nova matriu amb les posicions actualitzades dels jugadors.
     */
    private static String[][] moverJugadores(String[][] matriz) {
        int posActualJugador, mov;
        for (int i = 0; i < matriz.length; i++) {
            int largoMatriz = matriz[i].length-1;
            posActualJugador = comprobarPosicionJugador(matriz[i]);
            matriz[i][posActualJugador] = LIMPIAR;
            mov = randomNum(5) + posActualJugador;
            if (mov > largoMatriz)
                mov = largoMatriz;
            matriz[i][mov]= JUGADOR;

        }
        return matriz;
    }

    /**
     * Genera un número aleatorio entre 0 y (max*max)/max.
     * @param max el número máximo deseado para el rango del número aleatorio
     * @return el número aleatorio generado
     */
    private static int randomNum( int max) {
        return (int) (Math.random() * (max*max) / max);
    }

    /**
     * Neteja la matriu de joc, col·locant els emojis corresponents a cada posició.
     * El primer i últim element de cada fila corresponen al jugador i la meta, respectivament,
     * mentre que els altres elements s'identifiquen com a netes.
     * @param matriz  la matriu de strings amb els emojis corresponents a cada element del joc
     * @return la matriu original amb les caselles netejades i amb els emojis corresponents
     */
    private static String[][] limpiarMatriz( String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (j == 0)
                    matriz[i][j] = JUGADOR;
                else if (j == matriz[i].length-1) {
                    matriz[i][j] = META;
                } else
                    matriz[i][j]= LIMPIAR;
            }
        }
        return matriz;
    }

    /**
     * Mostra una matriu de strings amb els emojis corresponents a cada element del joc
     * i els seus respectius noms de jugador.
     * @param matriz la matriu de strings amb els emojis corresponents a cada element del joc
     * @param jugadores la llista de noms dels jugadors
     * @return la matriu original
     */
    public static String[][] mostrarStringMatriz(String[][] matriz, ArrayList<String> jugadores){
        System.out.println("⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println(" ➡️ "+jugadores.get(i));
        }
        return matriz;
    }

    /**
     * Llegeix un enter de l'entrada estàndard i retorna el valor llegit només si es troba entre els valors mínim i màxim especificats.
     * Si l'entrada no és un enter o està fora del rang, es mostra un missatge d'error i es demana una nova entrada.
     * @param missatge el missatge que es mostra a l'usuari per demanar l'entrada
     * @param min el valor mínim que l'entrada pot tenir
     * @param max el valor màxim que l'entrada pot tenir
     * @return el valor enter llegit de l'entrada estàndard
     */
    public static int llegirInt(String missatge, int min, int max) {
        Scanner llegir = new Scanner(System.in);
        int x = 0;
        boolean valorCorrecte = false;
        do{
            System.out.print(missatge);
            valorCorrecte = llegir.hasNextInt();
            if (!valorCorrecte){
                System.out.println("ERROR: Valor no enter.");
                llegir.nextLine();
            }else{ // Tinc un enter
                x = llegir.nextInt();
                llegir.nextLine();
                if (x < min || x > max){
                    System.out.println("Opció no vàlida");
                    valorCorrecte = false;
                }
            }
        }while(!valorCorrecte);

        return x;
    }
}