import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class Main {
    public static Scanner in = new Scanner(System.in);
    /**
     * Метод створення та заповнення з клавіатури масиву об'єктів Phone
     * @param list - сам масив
     */
    static void CreateList (Phone list[]){
        for (int n = 0; n < list.length; n++){
            list[n] = new Phone();
            list[n].InputInfo();
        }
        System.out.println("\n Введені вами дані:");
        for (int n = 0; n < list.length; n++){
            System.out.println("\n Абонент "+ (n+1) +list[n]);
        }
    }
    /**
     * Метод введення числа з перевіркою чи не є воно від'ємним
     * @param info - строка з повідомленням, що саме треба ввести
     * @return - повертає значення, яке ввів користувач
     */
    static int InputNum (String info){
        int num;
        while (true){
            System.out.print(" Введіть" + info + ": ");
            num = in.nextInt();
            if (num > 0)
                break;
            System.out.print(" Дане число є менше 1, повторіть введення.\n");
        }
        return num;
    }
    /**
     * Метод, який перевіряє, чи виведено хоча б один елемент масиву
     * @param pr - к-сть виведених елементів
     * @param message - строка, яка сповіщає за якою саме характеристикою немає елементів масиву
     */
    static void IsPrinted (int pr, String message){
        if (pr == 0) {
            System.out.println("\n Абонентів, які " + message + " немає.");
        }
    }
    /**
     * Завдання А. Вивід елементів масиву, які мають більше хвилин розмову ніж заданий користувачем ліміт
     * @param arr - масив елементів об'єктів класу Phone
     */
    static void TaskA(Phone arr[]){
        int limit, print = 0;
        System.out.println("\n Завдання А. Абоненти, які мають більшу к-сть міських розмов ніж дозволена: \n");
        limit = InputNum(" обмеження у часі міських розмов");
        for (Phone curr: arr){
            print += curr.IsUnderLim(limit);
        }
        IsPrinted(print, "використали більшу к-сть міських розмов");
    }
    /**
     * Завдання Б. Вивід абонентів, які користуються міжміським зв'язком
     * @param arr - масив елементів класу
     */
    static void TaskB (Phone arr[]){
        int print = 0;
        System.out.println("\n Завдання Б. Визначення абонентів, які користуються міжміським зв'язком: \n");
        for (Phone curr: arr){
            if (curr.getOutCity() > 0){
                System.out.println(curr);
                print++;
            }
        }
        IsPrinted(print, "користуються міжміським зв'язком");
    }
    /**
     * Завдання В. Приймаємо від користувача [min max] проміжку, шукаємо абонентів,
     * чий номер рахунку знаходиться у вказаному діапазоні
     * @param arr - масив елементів класу
     */
    static void TaskC (Phone arr[]){
        long[] range = new long[2]; int print = 0;
        System.out.println("\n Завдання В. Абоненти діапазон номера рахунку яких належить заданому діапазону: \n");
        while (true) {
            System.out.print(" Вкажіть діапазон для номера рахунку абонентів (у форматі: min max): ");
            for (int n = 0; n < 2; n++){
                range[n] = in.nextLong();
            }
            if (range[0] > 0 && range[1] > 0) {
                while (range[1] < range[0]){
                    System.out.println(" Максимум не може бути менше мінімума.\n");
                    System.out.print(" Введіть максимум: ");
                    range[1] = in.nextLong();
                }
                break;
            }
            System.out.println(" Мінімум та максимум мають бути більше 0.\n");
        }
        for (Phone curr: arr){
            print += curr.IsInRange(range[0], range[1]);
        }
        IsPrinted(print, "мають номер рахунка у вказаному діапазоні");
    }
    public static void main(String[] args) {
        int numb;
        numb = InputNum(" к-сть абонентів");
        Phone[] arr = new Phone[numb];
        CreateList(arr);
        TaskA(arr);
        TaskB(arr);
        TaskC(arr);
    }
}