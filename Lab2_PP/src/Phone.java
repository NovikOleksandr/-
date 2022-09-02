import java.util.Scanner;
public class Phone {
    private String FirstName, LastName, Surname; //Прізвище, ім'я та по батькові абонента
    private long id, accountID; // ID та номер рахунку
    private int InCity, OutCity; // Час міських та міжміських розмов
    /**
     * Метод введення даних з клавіатури інформації про
     * одного екземпляра класу
     */
    public void InputInfo (){
        Scanner info = new Scanner(System.in);
        System.out.print("\n Введіть ім'я абонента: ");
        FirstName = info.nextLine();
        System.out.print(" Введіть прізвище абонента: ");
        Surname = info.nextLine();
        System.out.print(" Введіть по батькові абонента: ");
        LastName = info.nextLine();
        System.out.print(" Введіть ID: ");
        id = info.nextLong();
        System.out.print(" Введіть номер рахунку: ");
        accountID = info.nextLong();
        System.out.print(" Введіть час міських розмов (у хв.): ");
        InCity = info.nextInt();
        System.out.print(" Введіть час міжміських розмов (у хв.): ");
        OutCity = info.nextInt();
    }
    /**
     * Метод перевірки чи абонент не перетнув обмеження
     * у часі міських розмов
     * @param lim - обмеження, введене користувачем
     * @return Якщо абонент має більше хвилин ніж задане обмеження, то +1, якщо ж ні +0
     */
    public int IsUnderLim (int lim){
        if (this.InCity > lim) {
            System.out.println(this);
            return 1;
        }
        return 0;
    }
    /**
     * Метод перевірки чи номер рахунку абонента перебуває у проміжку заданому користувачем
     * @param min - мінімальне можливе значення
     * @param max - максимальне можливе значення
     * @return +1, якщо номер абонента є у проміжку, +0, якщо не є
     */
    public int IsInRange (long min, long max){
        if (accountID >= min && accountID <= max) {
            System.out.println(this);
            return 1;
        }
        return 0;
    }
    /**
     * Метод toString, для виведення об'єкта однією строкою
     * @return саму строку, в якій зберігається інформація про об'єкт
     */
    @Override
    public String toString() {
        return "\n Ім'я: " + FirstName +
                ";\n Прізвище: " + Surname +
                ";\n По батькові: " + LastName +
                ";\n ID: " + id +
                ";\n Номер рахунку: " + accountID +
                ";\n Час міських розмов (у хв.): " + InCity +
                ";\n Час міжміських розмов (у хв.): " + OutCity;
    }
    /**
     * Сетур для ID
     * @param id - значення, яке записується сетером для змінної
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Сетер для номера рахунку
     * @param accountID - значення, яке буде встановлено
     */
    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }
    /**
     * Сетер для імені абоненту
     * @param firstName - ім'я абоненту
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    /**
     * Сетер, для значення к-сті хвилин міських розмов користувача
     * @param inCity - значення, яке задається
     */
    public void setInCity(int inCity) {
        InCity = inCity;
    }
    /**
     * Сетер, який задає по батькові
     * @param lastName - саме значення
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    /**
     * Сетер, для значення часу міжміських розмов
     * @param outCity - саме значення
     */
    public void setOutCity(int outCity) {
        OutCity = outCity;
    }
    /**
     * Сетер для значення прізвища
     * @param surname - змінна, яка задає значення
     */
    public void setSurname(String surname) {
        Surname = surname;
    }
    /**
     * Гетер к-сті хвилин міських розмов
     * @return - к-сть =вилин міських розмов
     */
    public int getInCity() {
        return InCity;
    }
    /**
     * Гетер к-сті хвилин міжміських розмов
     * @return - час міжміських розмов (у хвилинах)
     */
    public int getOutCity() {
        return OutCity;
    }
    /**
     * Гетер значення номера разунку
     * @return - число, яке означає номер рахунку абонента
     */
    public long getAccountID() {
        return accountID;
    }
    /**
     * Гетер ID абонента
     * @return - число, що означає ID абонента
     */
    public long getId() {
        return id;
    }
    /**
     * Гетер імені абонента
     * @return - ім'я абонента
     */
    public String getFirstName() {
        return FirstName;
    }
    /**
     * Гетер по батькові
     * @return - по батькові абонента
     */
    public String getLastName() {
        return LastName;
    }
    /**
     * Гетер прізвища
     * @return - прізвище абонента
     */
    public String getSurname() {
        return Surname;
    }
}

