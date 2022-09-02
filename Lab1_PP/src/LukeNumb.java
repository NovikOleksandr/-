
/**
 * Клас для представлення одного елемента послідовності Люка
 */
public class LukeNumb {
    /**
     * Змінна значення числа
     */
    private long number;
    /**
     * Змінна, яка описує номер числа у послідовності Люка
     */
    private int place;
    /**
     * Змінна, яка визначає чи є число трикутним
     */
    private boolean triangle;

    /**
     * Гетер для значення числа
     * @return Значення числа Люка
     */
    long GetNumber(){return number;}
    /**
     *
     * @return Визначає чи число трикутним
     */
    boolean GetTriangle() {return triangle; }
    /**
     *
     * @return Місце числа у послідовності
     */
    int GetPlace(){return place;}
    /**
     * Встановлення місця у послідовності
     * @param pc Номер числа у послідовності
     */
    void SetPlace(int pc){place = pc;}
    /**
     * Сетер для значення числа Люка
     * встановлення напряму через саме значення
     * @param num Значення яке ми встановлюємо
     */
    void SetNumber (long num){
        number = num;
        IsTriangle();
    }
    /**
     * Обчислення значення числа не напряму,
     * а через значення його 2 попередників
     * @param prev1 Значення числа на 2 позиції позаду
     * @param prev2 Значення числа на 1 позицію позаду
     */
    void SetNumber (long prev1, long prev2){
        number = prev1 + prev2;
        IsTriangle();
    }
    /**
     * Метод визначення характеристики числа Люка
     * чи воно є трикутним на основі пошуку кореня
     * квадратного рівняння
     */
    void IsTriangle (){
        long D = 1 + 8*number; // Пошук дискримінанта
        double x = (-1 + Math.sqrt(D))/2; // Пошук кореня
        if (x == (int)x){ // Якщо корінь - ціле число
            triangle = true; // Дане число "c" є трикутним
        }
        else{ // У іншому разі ні
            triangle = false;
        }
    }
}
