import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class MegaSenaVNW {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] numeros = new int[7];

        System.out.println("◘◘◘ MEGA-SENA VNW ◘◘◘");
        System.out.println("\nEscolha 7 números de 0 a 100:");

        for (int i = 0; i < 7; i++) {
            boolean valEnt = false;
            do {
                try {
                    System.out.print((i + 1) + "° Número " + ": ");
                    numeros[i] = Integer.parseInt(s.nextLine());

                    if (numeros[i] >= 0 && numeros[i] <= 100 && !isRepetido(numeros, i)) {
                        valEnt = true;
                    } else {
                        if (isRepetido(numeros, i)) {
                            System.out.println("Número já escolhido. Escolha outro.");
                        } else {
                            System.out.println("Digite um número entre 0 e 100.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Digite um número inteiro válido.");
                }
            } while (!valEnt);
        }

        int[] numWins = generateWinningNumbers();

        System.out.println("\nNúmeros sorteados: " + Arrays.toString(numWins));

        int numerosCertos = countCorrectNumbers(numeros, numWins);

        System.out.println("\nVocê acertou " + numerosCertos + " números!");

        switch (numerosCertos) {
            case 5:
                System.out.println("\n $ Parabéns! Você ganhou 10 mil reais! $ ");
                break;
            case 6:
                System.out.println("\n $ Parabéns! Você ganhou 50 mil reais! $ ");
                break;
            case 7:
                System.out.println("\n $ Parabéns! Você ganhou 200 mil reais! $ ");
                break;
            default:
                System.out.println("\n $ Não foi dessa vez. Tente novamente! $ ");
        }
    }

    private static int[] generateWinningNumbers() {
        int[] numVenc = new int[7];
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int novoNumero;
            do {
                novoNumero = random.nextInt(101);
            } while (!uniqueNumbers.add(novoNumero));

            numVenc[i] = novoNumero;
        }

        return numVenc;
    }

    private static int countCorrectNumbers(int[] numeros, int[] numWins) {
        int count = 0;

        for (int numeros1 : numeros) {
            if (Arrays.binarySearch(numWins, numeros1) >= 0) {
                count++;
            }
        }

        return count;
    }

    private static boolean isRepetido(int[] array, int index) {
        for (int i = 0; i < index; i++) {
            if (array[i] == array[index]) {
                return true;
            }
        }
        return false;
    }
}