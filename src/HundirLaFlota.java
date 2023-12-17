import java.util.Scanner;

    public class HundirLaFlota {
        public static char AGUA = ' ';
        public static char BARCO = 'B';
        public static char TOCADO = 'X';
        public static char FALLADO = 'F';

        public static void main(String[] args) {
            int filas = 10;
            int columnas = 10;

            System.out.println("¡Bienvenido al juego de Hundir la Flota!\n");
            System.out.println("Se trata de poner dos numeros, y te dire si has tocado agua, o un barco");

            char[][] tablero = inicializarTablero(filas, columnas);
            colocarBarcosAleatorios(tablero, 4);

            mostrarTablero(tablero);

            Scanner scanner = new Scanner(System.in);

            while (!todosBarcosHundidos(tablero)) {
                int[] coordenadas = pedirCoordenadas(scanner, filas, columnas);

                if (atacar(tablero, coordenadas)) {
                    System.out.println("¡Has acertado!");
                } else {
                    System.out.println("¡Has fallado!");
                }

                mostrarTablero(tablero);
            }

            System.out.println("¡Has ganado!");
        }

        public static char[][] inicializarTablero(int filas, int columnas) {
            char[][] tablero = new char[filas][columnas];

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    tablero[i][j] = AGUA;
                }
            }

            return tablero;
        }

        public static void colocarBarcosAleatorios(char[][] tablero, int cantidad) {
            for (int i = 0; i < cantidad; i++) {
                int x = (int) (Math.random() * tablero.length);
                int y = (int) (Math.random() * tablero[0].length);

                while (tablero[x][y] != AGUA) {
                    x = (int) (Math.random() * tablero.length);
                    y = (int) (Math.random() * tablero[0].length);
                }

                tablero[x][y] = BARCO;
            }
        }

        public static void mostrarTablero(char[][] tablero) {
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static int[] pedirCoordenadas(Scanner scanner, int filas, int columnas) {
            System.out.print("Introduce las coordenadas del ataque (fila, columna): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            while (!coordenadasValidas(x, y, filas, columnas)) {
                System.out.println("Coordenadas no válidas. Inténtalo de nuevo.");
                System.out.print("Introduce las coordenadas del ataque (fila, columna): ");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }

            return new int[]{x, y};
        }

        public static boolean coordenadasValidas(int x, int y, int filas, int columnas) {
            return x >= 0 && x < filas && y >= 0 && y < columnas;
        }

        public static boolean atacar(char[][] tablero, int[] coordenadas) {
            int x = coordenadas[0];
            int y = coordenadas[1];

            if (tablero[x][y] == BARCO) {
                tablero[x][y] = TOCADO;
                return true;
            } else {
                tablero[x][y] = FALLADO;
                return false;
            }
        }

        public static boolean todosBarcosHundidos(char[][] tablero) {
            for (char[] fila : tablero) {
                for (char c : fila) {
                    if (c == BARCO) {
                        return false;
                    }
                }
            }
            return true;
        }
    }