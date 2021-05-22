import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

class Library implements Serializable {
    List<Book> book;

    public Library() {
        book = new LinkedList<>();
    }

    private void addBook(Book bookAdding) {
        book.add(bookAdding);
        book.get(book.size() - 1).ID = book.size() - 1;
    }

    public void ShowBookListShort() {
        System.out.println("Skrócona lista książek");
        System.out.format("%-5s%-30s%-40s%s\n", "ID", "AUTOR", "TYTUŁ", "CZY WYPOŻYCZONA");
        for (Book value : this.book) {
            System.out.format("%-5d", value.ID);
            StringBuilder tempNameString = new StringBuilder();
            for (int j = 0; j < value.AutorFirstName.split(" ").length; j++) {
                tempNameString.append(value.AutorFirstName.split(" ")[j].charAt(0)).append(".");
            }
            System.out.format("%-30s", tempNameString + " " + value.AutorLastName);
            System.out.format("%-40s", value.Title);
            System.out.println((value.IsRent) ? "WYPOŻYCZONA" : "DOSTĘPNA");
        }
    }

    public void ShowBookListLong() {
        System.out.println("Pełna lista książek");
        System.out.format("%-5s%-30s%-30s%-40s%-40s%-15s%-20s%s\n", "ID", "IMIONA AUTORA", "NAZWISKO AUTORA", "TYTUŁ", "KATEGORIE", "ROK WYDANIA", "CZY WYPOŻYCZONA", "LICZNIK WYPOŻYCZEŃ");
        for (Book value : this.book) {
            System.out.format("%-5d", value.ID);
            System.out.format("%-30s", value.AutorFirstName);
            System.out.format("%-30s", value.AutorLastName);
            System.out.format("%-40s", value.Title);
            StringBuilder tempCategoryString = new StringBuilder();
            for (int j = 0; j < value.ThisBookCategories.size(); j++) {
                tempCategoryString.append(value.ThisBookCategories.get(j)).append("; ");
            }
            System.out.format("%-40s", tempCategoryString);
            System.out.format("%-15d", value.Year);
            System.out.format("%-20s", (value.IsRent) ? "WYPOŻYCZONA" : "DOSTĘPNA");
            System.out.println(value.RentCounter);
        }
    }

    private void ShowBookListLong(List<Book> SortList) {
        System.out.format("%-5s%-30s%-30s%-40s%-40s%-15s%-20s%s\n", "ID", "IMIONA AUTORA", "NAZWISKO AUTORA", "TYTUŁ", "KATEGORIE", "ROK WYDANIA", "CZY WYPOŻYCZONA", "LICZNIK WYPOŻYCZEŃ");
        for (Book value : SortList) {
            System.out.format("%-5d", value.ID);
            System.out.format("%-30s", value.AutorFirstName);
            System.out.format("%-30s", value.AutorLastName);
            System.out.format("%-40s", value.Title);
            StringBuilder tempCategoryString = new StringBuilder();
            for (int j = 0; j < value.ThisBookCategories.size(); j++) {
                tempCategoryString.append(value.ThisBookCategories.get(j)).append("; ");
            }
            System.out.format("%-40s", tempCategoryString);
            System.out.format("%-15d", value.Year);
            System.out.format("%-20s", (value.IsRent) ? "WYPOŻYCZONA" : "DOSTĘPNA");
            System.out.println(value.RentCounter);
        }
    }

    private List<Book> SortBookByRentCounter(List<Book> ListToSort) {
        List<Book> SortList = new LinkedList<>(ListToSort);
        SortList.sort((o1, o2) -> Integer.compare(o2.RentCounter, o1.RentCounter));
        return SortList;
    }

    public void SearchBookInDatabaseByTitle() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Jaki jest tytuł szukanej pozycji");
        System.out.print("TYTUŁ: ");
        String nazwisko = userInput.nextLine();
        System.out.println("ZNALEZIONE POZYCJE");
        System.out.format("%-5s%-30s%-30s%-40s%-40s%-15s%-20s%s\n", "ID", "IMIONA AUTORA", "NAZWISKO AUTORA", "TYTUŁ", "KATEGORIE", "ROK WYDANIA", "CZY WYPOŻYCZONA", "LICZNIK WYPOŻYCZEŃ");
        for (Book value : this.book) {
            if (value.Title.contains(nazwisko)) {
                System.out.format("%-5d", value.ID);
                System.out.format("%-30s", value.AutorFirstName);
                System.out.format("%-30s", value.AutorLastName);
                System.out.format("%-40s", value.Title);
                StringBuilder tempCategoryString = new StringBuilder();
                for (int j = 0; j < value.ThisBookCategories.size(); j++) {
                    tempCategoryString.append(value.ThisBookCategories.get(j)).append("; ");
                }
                System.out.format("%-40s", tempCategoryString);
                System.out.format("%-15d", value.Year);
                System.out.format("%-20s", (value.IsRent) ? "WYPOŻYCZONA" : "DOSTĘPNA");
                System.out.println(value.RentCounter);
            }
        }
        System.out.println();
    }

    public void SearchBookInDatabaseByAutor() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Jakie jest nazwisko autora szukanej pozycji");
        System.out.print("NAZWISKO: ");
        String nazwisko = userInput.nextLine();
        System.out.println("ZNALEZIONE POZYCJE");
        System.out.format("%-5s%-30s%-30s%-40s%-40s%-15s%-20s%s\n", "ID", "IMIONA AUTORA", "NAZWISKO AUTORA", "TYTUŁ", "KATEGORIE", "ROK WYDANIA", "CZY WYPOŻYCZONA", "LICZNIK WYPOŻYCZEŃ");
        for (Book value : this.book) {
            if (value.AutorLastName.contains(nazwisko)) {
                System.out.format("%-5d", value.ID);
                System.out.format("%-30s", value.AutorFirstName);
                System.out.format("%-30s", value.AutorLastName);
                System.out.format("%-40s", value.Title);
                StringBuilder tempCategoryString = new StringBuilder();
                for (int j = 0; j < value.ThisBookCategories.size(); j++) {
                    tempCategoryString.append(value.ThisBookCategories.get(j)).append("; ");
                }
                System.out.format("%-40s", tempCategoryString);
                System.out.format("%-15d", value.Year);
                System.out.format("%-20s", (value.IsRent) ? "WYPOŻYCZONA" : "DOSTĘPNA");
                System.out.println(value.RentCounter);
            }
        }
        System.out.println();
    }

    public void SortBookByAutorLastName() {
        List<Book> SortList = new LinkedList<>(book);
        SortList.sort((o1, o2) -> String.valueOf(o1.AutorLastName).compareTo(o2.AutorLastName));
        ShowBookListLong(SortList);
    }

    public void SortBookByTitle() {
        List<Book> SortList = new LinkedList<>(book);
        SortList.sort((o1, o2) -> String.valueOf(o1.Title).compareTo(o2.Title));
        ShowBookListLong(SortList);
    }

    public void SortBookByYear() {
        List<Book> SortList = new LinkedList<>(book);
        SortList.sort(Comparator.comparingInt(o -> o.Year));
        ShowBookListLong(SortList);
    }

    public void SortBookByRentCounter() {
        List<Book> SortList = new LinkedList<>(book);
        SortList.sort(Comparator.comparingInt(o -> o.RentCounter));
        ShowBookListLong(SortList);
    }

    public void SearchBookInDatabaseByCategory() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Jakia jest kategoria szukanej pozycji");
        System.out.print("KATEGORIA: ");
        String nazwisko = userInput.nextLine();
        System.out.println("ZNALEZIONE POZYCJE");
        System.out.format("%-5s%-30s%-30s%-40s%-40s%-15s%-20s%s\n", "ID", "IMIONA AUTORA", "NAZWISKO AUTORA", "TYTUŁ", "KATEGORIE", "ROK WYDANIA", "CZY WYPOŻYCZONA", "LICZNIK WYPOŻYCZEŃ");
        for (Book value : this.book) {
            if (value.ThisBookCategories.contains(nazwisko)) {
                System.out.format("%-5d", value.ID);
                System.out.format("%-30s", value.AutorFirstName);
                System.out.format("%-30s", value.AutorLastName);
                System.out.format("%-40s", value.Title);
                StringBuilder tempCategoryString = new StringBuilder();
                for (int j = 0; j < value.ThisBookCategories.size(); j++) {
                    tempCategoryString.append(value.ThisBookCategories.get(j)).append("; ");
                }
                System.out.format("%-40s", tempCategoryString);
                System.out.format("%-15d", value.Year);
                System.out.format("%-20s", (value.IsRent) ? "WYPOŻYCZONA" : "DOSTĘPNA");
                System.out.println(value.RentCounter);
            }
        }
        System.out.println();
    }

    public void userAddBook() throws IOException {
        Scanner userInput = new Scanner(System.in);
        String Title;
        String AutorFirstName;
        String AutorLastName;
        int Year;
        System.out.print("Podaj tytuł: ");
        Title = userInput.nextLine();
        System.out.print("Podaj imie autora: ");
        AutorFirstName = userInput.nextLine();
        System.out.print("Podaj nazwisko autora: ");
        AutorLastName = userInput.nextLine();
        System.out.print("Podaj rok wydania: ");
        try {
            Year = userInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wprowadziłeś nieprawidłową liczbę, dlatego rok ustawiłem na 1900 możesz potem zedytować rok tej książki ...");
            Year = 1900;
            userInput.nextLine();
        }
        this.addBook(new Book(Title, AutorFirstName, AutorLastName, Year));
        do {
            System.out.println("Wybierz kategorie książki (wpisując jej numer) spośród poniżej podanych \n");

            for (int i = 0; i < Book.Categories.size(); i++) {
                System.out.print(i + 1);
                System.out.print(". " + Book.Categories.get(i) + ";   ");
            }
            System.out.println("\nJeżeli chcesz dodać nową kategorie napisz \"ADD\" \nNatomiast jeżeli skończyłeś już dodawać kategorie napisz \"EXIT\"");
            String tempStr = userInput.next();
            if (tempStr.equalsIgnoreCase("ADD")) {
                Book.AddCategories();
            } else if (tempStr.equalsIgnoreCase("EXIT")) {
                break;
            } else if (isInteger(tempStr)) {
                if (Integer.parseInt(tempStr) > 0 && Integer.parseInt(tempStr) <= Book.Categories.size())
                    this.book.get(this.book.size() - 1).ThisBookCategories.add(Book.Categories.get(Integer.parseInt(tempStr) - 1));
                else
                    System.out.println("BLEDNA FORMULA upewnij sie czy na pewno jest to liczba z zakresu 1-" + Book.Categories.size() + " oraz jedna z komend \"ADD\" bądź też \"EXIT\"");
            } else
                System.out.println("BLEDNA FORMULA upewnij sie czy na pewno jest to liczba z zakresu 1-" + Book.Categories.size() + " oraz jedna z komend \"ADD\" bądź też \"EXIT\"");

        } while (true);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public void SaveLibraryDatabase() throws IOException {
        File fileRepair = new File("database.bin");
        if (fileRepair.exists()) {
            System.out.println("Tworze backup poprzedniej wersji bazy danych");
            Files.copy(Paths.get("database.bin"), Paths.get("database.old"), StandardCopyOption.REPLACE_EXISTING);
            fileRepair.delete();
        }
        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("database.bin"))) {
            file.writeObject(UserTextInterface.biblioteka.book);
        }
    }

    public void RemoveBookFromLibraryDatabase() {
        System.out.println("Pod spodem wyświetla się szczegółowa lista książek");
        UserTextInterface.biblioteka.ShowBookListLong();
        System.out.println("Aby skasować książkę z systemu napisz jej nr id. Jeżeli jednak nie chcesz usuwać żadnej książki zapisz liczbe " + this.book.size());
        System.out.print("WYBÓR: ");
        int UserChoice = -1;
        try {
            UserChoice = UserTextInterface.userInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ!");
            UserTextInterface.userInput.nextLine();
        }
        if (UserChoice >= this.book.size() || UserChoice < 0) return;
        this.book.remove(UserChoice);
    }

    public void EditBookInLibraryDatabase() throws IOException {
        Scanner inputData = new Scanner(System.in);
        System.out.println("Pod spodem wyświetla się szczegółowa lista książek");
        UserTextInterface.biblioteka.ShowBookListLong();
        System.out.println("Aby zedytować książke wpierw napisz mi jaki indeks ma książka do edycji. Jeżeli jednak nie chcesz edytować żadnej książki zapisz liczbe " + this.book.size());
        System.out.print("Index książki: ");
        int UserChoice;
        try {
            UserChoice = UserTextInterface.userInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !");
            UserTextInterface.userInput.nextLine();
            UserChoice = this.book.size();
        }
        if (UserChoice >= this.book.size() || UserChoice < 0) return;
        System.out.println("Co chcesz edytować w wybranej pozycji:");
        System.out.print("1. Tytuł\t");
        System.out.print("2. Imiona autora\t");
        System.out.print("3. Nazwisko autora\t");
        System.out.print("4. Kategorie\t");
        System.out.print("5. Rok wydania\t");
        System.out.println("6. Anulowanie edycji\t");
        System.out.print("WYBÓR: ");
        int choice;
        try {
            choice = UserTextInterface.userInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("PODANA WARTOŚĆ NEI JEST LICZBĄ !");
            UserTextInterface.userInput.nextLine();
            choice = 6;
        }
        switch (choice) {
            case 1:
                System.out.println("Nowy tytuł: ");
                UserTextInterface.biblioteka.book.get(UserChoice).Title = inputData.nextLine();
                break;
            case 2:
                System.out.println("Nowe imiona autora: ");
                UserTextInterface.biblioteka.book.get(UserChoice).AutorFirstName = inputData.nextLine();
                break;
            case 3:
                System.out.println("Nowe nazwisko autora: ");
                UserTextInterface.biblioteka.book.get(UserChoice).AutorLastName = inputData.nextLine();
                break;
            case 4:
                System.out.println("Usuwam poprzednie przyporządkowane kategorie");
                UserTextInterface.biblioteka.book.get(UserChoice).ThisBookCategories.clear();
                do {
                    System.out.println("Wybierz kategorie książki (wpisując jej numer) spośród poniżej podanych \n");

                    for (int i = 0; i < Book.Categories.size(); i++) {
                        System.out.print(i + 1);
                        System.out.print(". " + Book.Categories.get(i) + ";   ");
                    }
                    System.out.println("\nJeżeli chcesz dodać nową kategorie napisz \"ADD\" \nNatomiast jeżeli skończyłeś już dodawać kategorie napisz \"EXIT\"");
                    String tempStr = UserTextInterface.userInput.next();
                    if (tempStr.equalsIgnoreCase("ADD")) {
                        Book.AddCategories();
                    } else if (tempStr.equalsIgnoreCase("EXIT")) {
                        break;
                    } else if (Library.isInteger(tempStr)) {
                        if (Integer.parseInt(tempStr) > 0 && Integer.parseInt(tempStr) <= Book.Categories.size())
                            this.book.get(UserChoice).ThisBookCategories.add(Book.Categories.get(Integer.parseInt(tempStr) - 1));
                        else
                            System.out.println("BLEDNA FORMULA upewnij sie czy na pewno jest to liczba z zakresu 1-" + Book.Categories.size() + " oraz jedna z komend \"ADD\" bądź też \"EXIT\"");
                    } else
                        System.out.println("BLEDNA FORMULA upewnij sie czy na pewno jest to liczba z zakresu 1-" + Book.Categories.size() + " oraz jedna z komend \"ADD\" bądź też \"EXIT\"");

                } while (true);
                break;
            case 5:
                System.out.println("Nowy rok wydania: ");
                try {
                    UserTextInterface.biblioteka.book.get(UserChoice).Year = inputData.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ ! USTAWIAM ROK NA 1900");
                    inputData.nextLine();
                    UserTextInterface.biblioteka.book.get(UserChoice).Year = 1900;
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Błędny wybór opcji");
        }
    }

    public void ShowExtendDataAboutBoooks() {
        System.out.println("OTO PODSUMOWANIE INFORMACJI O KSIĄŻKACH ZNAJDUJĄCYCH SIĘ W BIBLIOTECE");
        System.out.println("\nLiczba wszystkich książek: " + this.book.size());
        int rentcounter = 0;
        for (Book value : this.book) {
            if (value.IsRent) rentcounter++;
        }
        System.out.println("Liczba aktualnie wyporzyczonych książek: " + rentcounter);
        rentcounter = 0;
        for (Book value : this.book) {
            rentcounter += value.RentCounter;
        }
        System.out.println("Liczba całkowitej liczby wyporzyczeń: " + rentcounter);
    }

    public void Show5MostCommonBooks() {
        List<Book> SortList = new LinkedList<>(book);
        SortList.sort((o1, o2) -> Integer.compare(o2.RentCounter, o1.RentCounter));
        System.out.println("NAJCZĘŚCIEJ WYPORZYCZANYMI KSIĄŻKAMI BYŁY:");
        System.out.format("%-5s%-30s%-40s%s\n", "ID", "AUTOR", "TYTUŁ", "LICZBA WYPORZYCZEŃ");
        for (int i = 0, h = 0; i < SortList.size() && h < 5; i++, h++) {
            System.out.format("%-5d", SortList.get(i).ID);
            StringBuilder tempNameString = new StringBuilder();
            for (int j = 0; j < SortList.get(i).AutorFirstName.split(" ").length; j++) {
                tempNameString.append(SortList.get(i).AutorFirstName.split(" ")[j].charAt(0)).append(".");
            }
            System.out.format("%-30s", tempNameString + " " + SortList.get(i).AutorLastName);
            System.out.format("%-40s", SortList.get(i).Title);
            System.out.println(SortList.get(i).RentCounter);
            if (SortList.size() < i + 1)
                if (h == 4 && SortList.get(i).RentCounter == SortList.get(i + 1).RentCounter) h -= 1;
        }
    }

    public void ImportBookFromFile() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Napisz z jakiego pliku mają zostać zimportowane dane. Nazwe pliku zapisz w formacie \"*.*\"");
        System.out.print("PLIK: ");
        String path = userInput.nextLine();
        File tempchecker = new File(path);
        if (!tempchecker.exists()) {
            System.out.println("Plik o podanej nazwie nie istnieje!");
            return;
        }
        try (Scanner file = new Scanner(new BufferedReader(new FileReader(path)))) {
            while (file.hasNextLine()) {
                String[] separatedImportedData = file.nextLine().split(";");
                String[] firstLastAuthorName = separatedImportedData[0].split(",");

                this.addBook(new Book(separatedImportedData[1].substring(1), firstLastAuthorName[0], firstLastAuthorName[1].substring(1), (isInteger(separatedImportedData[2].trim())) ? Integer.parseInt(separatedImportedData[2].trim()) : 1900));
                String[] categories = separatedImportedData[3].split(",");
                for (String category : categories) {
                    if (!Book.Categories.contains(category.substring(1))) {
                        System.out.println("Dodaje nową kategorie");
                        Book.Categories.add(category.substring(1));
                    }
                    this.book.get(this.book.size() - 1).ThisBookCategories.add(category.substring(1));
                }
                File fileRepair = new File("category.bin");
                if (fileRepair.exists()) {
                    System.out.println("Tworze backup poprzedniej bazy kategorii");
                    Files.copy(Paths.get("category.bin"), Paths.get("category.old"), StandardCopyOption.REPLACE_EXISTING);
                }
                try (ObjectOutputStream fileCategorySave = new ObjectOutputStream(new FileOutputStream("category.bin"))) {
                    fileCategorySave.writeObject(Book.Categories);
                }
            }
        }
    }

    public void ExportBooksFromDatabase() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Jak nazwać plik z wyeksportowanymi danymi - nazwa w formacie\"*.*\"");
        System.out.print("PLIK: ");
        String path = userInput.nextLine();
        try (PrintWriter newFile = new PrintWriter(new FileWriter(path))) {
            for (Book currentlyBook : this.book) {
                StringBuilder categories = new StringBuilder();
                for (String category : currentlyBook.ThisBookCategories) categories.append(", ").append(category);
                newFile.println(currentlyBook.AutorFirstName + ", " + currentlyBook.AutorLastName + "; " + currentlyBook.Title + "; " + currentlyBook.Year + ";" + categories.substring(1));
            }
        }
    }

    private List<Book> ConnectBooksWithSameAuthorAndTitle() {
        List<Book> ConectedBooks = new ArrayList<>();
        for (Book copyBook : book) {
            ConectedBooks.add(new Book(copyBook.Title, copyBook.AutorFirstName, copyBook.AutorLastName, copyBook.Year));
            ConectedBooks.get(ConectedBooks.size() - 1).ThisBookCategories = copyBook.ThisBookCategories;
            ConectedBooks.get(ConectedBooks.size() - 1).RentCounter = copyBook.RentCounter;
        }
        for (int j = 0; j < ConectedBooks.size(); j++) {
            for (int i = j + 1; i < ConectedBooks.size(); i++) {
                if ((ConectedBooks.get(i).Title.equals(ConectedBooks.get(j).Title)) &&
                        (ConectedBooks.get(i).AutorFirstName.equals(ConectedBooks.get(j).AutorFirstName)) &&
                        (ConectedBooks.get(i).AutorLastName.equals(ConectedBooks.get(j).AutorLastName))) {

                    ConectedBooks.get(j).RentCounter += ConectedBooks.get(i).RentCounter;
                    ConectedBooks.remove(i);
                }
            }
        }
        return ConectedBooks;
    }

    private List<Book> ConnectBooksWithSameAuthor() {
        List<Book> ConectedBooks = new ArrayList<>();
        for (Book copyBook : book) {
            ConectedBooks.add(new Book(copyBook.Title, copyBook.AutorFirstName, copyBook.AutorLastName, copyBook.Year));
            ConectedBooks.get(ConectedBooks.size() - 1).ThisBookCategories = copyBook.ThisBookCategories;
            ConectedBooks.get(ConectedBooks.size() - 1).RentCounter = copyBook.RentCounter;
        }
        for (int j = 0; j < ConectedBooks.size(); j++) {
            for (int i = j + 1; i < ConectedBooks.size(); i++) {
                if ((ConectedBooks.get(i).AutorFirstName.equals(ConectedBooks.get(j).AutorFirstName)) &&
                        (ConectedBooks.get(i).AutorLastName.equals(ConectedBooks.get(j).AutorLastName))) {

                    ConectedBooks.get(j).RentCounter += ConectedBooks.get(i).RentCounter;
                    ConectedBooks.remove(i);
                }
            }
        }
        return ConectedBooks;
    }

    public void Show5MostCommonBooksInCategories() {
        List<Book> ConectedBooks = SortBookByRentCounter(ConnectBooksWithSameAuthorAndTitle());
        System.out.println("Wyświetlanie 5 najbardziej poczytnych książek z każdej kategorii tematycznej");
        System.out.println("Kategorie: ");
        Book.ScrollCategories();
        System.out.println();
        for (String category : Book.Categories) {
            System.out.println(category + ": ");
            int i = 1;
            for (Book sortedConectedBook : ConectedBooks) {
                if (sortedConectedBook.ThisBookCategories.contains(category) && sortedConectedBook.RentCounter > 0) {
                    System.out.print(i + ". ");
                    i++;
                    System.out.println(sortedConectedBook.AutorFirstName + " " + sortedConectedBook.AutorLastName + " " + sortedConectedBook.Title + " " + sortedConectedBook.RentCounter + " - wyporzyczeń");
                    if (i >= 5) break;
                }
            }
        }
    }

    public void Show5MostCommonAutors() {
        List<Book> ConnectedBooks = SortBookByRentCounter(ConnectBooksWithSameAuthor());
        System.out.println("Wyświetlanie 5 najbardziej poczytnych autorów");
        for (int i = 0; i < 5 && i < ConnectedBooks.size(); i++) {
            if (ConnectedBooks.get(i).RentCounter > 0) {
                System.out.print(i + 1 + ". ");
                System.out.print(ConnectedBooks.get(i).AutorFirstName + " ");
                System.out.print(ConnectedBooks.get(i).AutorLastName + " ");
                System.out.println(ConnectedBooks.get(i).RentCounter + "- wypożyczeń");
            }
        }
    }
}
