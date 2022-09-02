import java.util.Scanner;
class Main {
    /**
     *  Функція створення масиву послідовності чисел Люка
     * @param arr Масив екземплярів класу чисел Люка
     * @param count К-сть чисел Люка, яку задав користувач
     */
    public static void CreateLukeArr(LukeNumb[] arr, int count){
        if (count == 1){ // Якщо користувач захотів лише 1 число з послідовності
            arr[0] = new LukeNumb();
            arr[0].SetNumber(2); // Задаю значення числа
            arr[0].SetPlace(1); // Його позицію в послідовності
        }
        else{
            arr[0] = new LukeNumb(); //Створюємо два перших числа в послідовності
            arr[1] = new LukeNumb(); // Та далі задаю їх значення та місце в послідовності
            arr[0].SetNumber(2); arr[1].SetNumber(1);
            arr[0].SetPlace(1); arr[1].SetPlace(2);
            for (int n = 1, j = 2; j < count; j++, n++){ // В циклі до введеного користувачем номера
                arr[j] = new LukeNumb(); // Заповнюю масив елементами послідовності
                arr[j].SetNumber(arr[n].GetNumber(), arr[n-1].GetNumber());
                arr[j].SetPlace(j + 1);
            }
        }
    }
    public static void main(String[] args) {
        int numb_of = 0, out = 0;
        Scanner input = new Scanner(System.in);
        while (numb_of < 1){  // Доки користувач не введе задовільну к-сть елементів
            System.out.println("\n Введіть N - кількість елементів послідовності: ");
            numb_of = input.nextInt();
        }
        LukeNumb[] arr = new LukeNumb[numb_of];
        CreateLukeArr(arr, numb_of);
        System.out.println("\n Числа з послдовності Люка, які є трикутними: ");
        for (int i = 0; i < numb_of; i++){
            if (arr[i].GetTriangle()){
                System.out.print(arr[i].GetNumber()+" ");
                out++;
            }
        }
        if (out == 0){
            System.out.println("\n В даній кількості N перших елементів послідовності Люка немає трикутних.");
        }
    }
}