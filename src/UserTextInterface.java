import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class UserTextInterface {
    static Scanner userInput = new Scanner(System.in);
    static Library biblioteka = new Library();

    public static void UserMainTextInterface() throws IOException, ClassNotFoundException {
        File file = new File("database.bin");
        if (file.exists()) {
            try (ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream("database.bin"))) {
                UserTextInterface.biblioteka.book = (List<Book>) fileInput.readObject();

            }
        }
        while (true) {
            System.out.println("Witaj w Systemie Obsługi Biblioteki");
            System.out.println("Z poniższego menu wybierz czynność jaką chcesz wykonać w systemie :");
            System.out.println("1. Zarządzanie kategoriami");
            System.out.println("2. Zarządzanie bazą danych książek");
            System.out.println("3. Zarządzanie wypożyczeniami książek");
            System.out.println("4. Przeszukiwanie bazy danych książek");
            System.out.println("5. Funkcje dodatkowe");
            System.out.println("6. Zakończenie działania programu\n");
            System.out.print("WYBÓR: ");
            try {
                switch (userInput.nextInt()) {
                    case 1:
                        UserCategoryTextMenu();
                        break;
                    case 2:
                        UserDatabaseTextMenu();
                        break;
                    case 3:
                        UserRentTextMenu();
                        break;
                    case 4:
                        UserSerchingTextMenu();
                        break;
                    case 5:
                        UserExtendingFunctions();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Niewłaściwy nr opcij z menu kontekstowaego");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }
    }

    private static void UserCategoryTextMenu() throws IOException {
        while (true) {
            System.out.println("WYBRAŁEŚ ZARZĄDZANIE KATEGORIAMI.\nZ poniższego menu wybierz czynność jaką chcesz wykonać w systemie :");
            System.out.println("1. Przejrzenie istniejących kategorii");
            System.out.println("2. Dodanie nowych kategorii");
            System.out.println("3. Usunięcie wybranych kategorii");
            System.out.println("4. Powrót do menu głównego\n");
            System.out.print("WYBÓR: ");
            try {
                switch (userInput.nextInt()) {
                    case 1:
                        Book.ScrollCategories();
                        break;
                    case 2:
                        Book.AddCategories();
                        break;
                    case 3:
                        Book.RemoveCategories();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Niewłaściwy nr opcij z menu kontekstowaego");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }
    }

    private static void UserDatabaseTextMenu() throws IOException {
        while (true) {
            System.out.println("WYBRAŁEŚ ZARZĄDZANIE BAZĄ DANYCH KSIĄŻEK.\nZ poniższego menu wybierz czynność jaką chcesz wykonać w systemie :");
            System.out.println("1. Wyświetlenie listy książek (tryb skrócony)");
            System.out.println("2. Wyświetlenie listy książek (tryb szczegółowy)");
            System.out.println("3. Dodawanie książek do systemu biblioteki");
            System.out.println("4. Zapisanie bazy danych książek");
            System.out.println("5. Edycja książek w systemie biblioteki");
            System.out.println("6. Kasowanie książek z systemiu biblioteki");
            System.out.println("7. Importowanie książek do systemu biblioteki");
            System.out.println("8. Eksport książek z systemu biblioteki");
            System.out.println("9. Powrót do menu głównego\n");
            System.out.print("WYBÓR: ");
            try {
                switch (userInput.nextInt()) {
                    case 1:
                        UserTextInterface.biblioteka.ShowBookListShort();
                        break;
                    case 2:
                        UserTextInterface.biblioteka.ShowBookListLong();
                        break;
                    case 3:
                        UserTextInterface.biblioteka.userAddBook();
                        break;
                    case 4:
                        UserTextInterface.biblioteka.SaveLibraryDatabase();
                        break;
                    case 5:
                        UserTextInterface.biblioteka.EditBookInLibraryDatabase();
                        break;
                    case 6:
                        UserTextInterface.biblioteka.RemoveBookFromLibraryDatabase();
                        break;
                    case 7:
                        UserTextInterface.biblioteka.ImportBookFromFile();
                        break;
                    case 8:
                        UserTextInterface.biblioteka.ExportBooksFromDatabase();
                        break;
                    case 9:
                        return;
                    default:
                        System.out.println("Niewłaściwy nr opcij z menu kontekstowaego");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }
    }

    private static void UserRentTextMenu() {
        while (true) {
            System.out.println("Poniżej znajduje się szczegółowa lista książek");
            UserTextInterface.biblioteka.ShowBookListLong();
            System.out.println("Aby zmienić status wyporzyczenia książki podaj jej index. Aby zakończyć operacje wyporzyczeń napisz -1");
            System.out.print("WYBÓR: ");
            try {
                int userChoiceOperation = UserTextInterface.userInput.nextInt();
                if (userChoiceOperation < 0 || userChoiceOperation >= UserTextInterface.biblioteka.book.size()) break;
                UserTextInterface.biblioteka.book.get(userChoiceOperation).IsRent = !UserTextInterface.biblioteka.book.get(userChoiceOperation).IsRent;
                if (UserTextInterface.biblioteka.book.get(userChoiceOperation).IsRent)
                    UserTextInterface.biblioteka.book.get(userChoiceOperation).RentCounter++;
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }

    }

    private static void UserSerchingTextMenu() {
        while (true) {
            System.out.println("WYBRAŁEŚ PRZESZUKIWANIE BAZY DANYCH KSIĄŻEK.\nZ poniższego menu wybierz według jakiego kruterium baza ma zostać przeszukana :");
            System.out.println("1. Nazwisko autora");
            System.out.println("2. Tytuł");
            System.out.println("3. Kategoria tematyczna");
            System.out.println("4. Powrót do menu głównego\n");
            System.out.print("WYBÓR: ");
            try {
                switch (userInput.nextInt()) {
                    case 1:
                        UserTextInterface.biblioteka.SearchBookInDatabaseByAutor();
                        break;
                    case 2:
                        UserTextInterface.biblioteka.SearchBookInDatabaseByTitle();
                        break;
                    case 3:
                        UserTextInterface.biblioteka.SearchBookInDatabaseByCategory();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Niewłaściwy nr opcij z menu kontekstowaego");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }
    }

    private static void UserSortByFunctions() {
        while (true) {
            System.out.println("WYBRAŁEŚ SORTOWANIE BAZY DANYCH KSIĄŻEK.\nZ poniższego menu wybierz według jakiego kruterium baza ma zostać przesortowana :");
            System.out.println("1. Nazwisko autora");
            System.out.println("2. Tytuł");
            System.out.println("3. Rok wydania");
            System.out.println("4. Liczba wyporzyczeń");
            System.out.println("5. Powrót do menu głównego\n");
            System.out.print("WYBÓR: ");
            try {
                switch (userInput.nextInt()) {
                    case 1:
                        UserTextInterface.biblioteka.SortBookByAutorLastName();
                        break;
                    case 2:
                        UserTextInterface.biblioteka.SortBookByTitle();
                        break;
                    case 3:
                        UserTextInterface.biblioteka.SortBookByYear();
                        break;
                    case 4:
                        UserTextInterface.biblioteka.SortBookByRentCounter();
                        return;
                    case 5:
                        return;
                    default:
                        System.out.println("Niewłaściwy nr opcij z menu kontekstowaego");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }
    }

    private static void UserExtendingFunctions() {
        while (true) {
            System.out.println("WYBRAŁEŚ MENU Z DODATKOWYMI FUNKCJAMI SYSTEMU BAZY DANYCH.\nZ poniższego menu wybierz którą z dodatkowych funkcji programu chcesz ururchomić");
            System.out.println("1. Wyświetlanie listy książek posortowanej według podanego kryterium");
            System.out.println("2. Wyświetlanie liczby wszystkich książek, liczby obecnie wypożyczonych oraz całkowitej liczby wypożyczeń");
            System.out.println("3. Wyświetlanie 5 najczęściej wypożyczanych egzemplarzy książek");
            System.out.println("4. Wyświetlanie 5 najbardziej poczytnych książek z każdej kategorii tematycznej");
            System.out.println("5. Wyświetlanie 5 najbardziej poczytnych autorów");
            System.out.println("6. Powrót do menu głównego\n");
            System.out.print("WYBÓR: ");
            try {
                switch (userInput.nextInt()) {
                    case 1:
                        UserSortByFunctions();
                        break;
                    case 2:
                        UserTextInterface.biblioteka.ShowExtendDataAboutBoooks();
                        break;
                    case 3:
                        UserTextInterface.biblioteka.Show5MostCommonBooks();
                        break;
                    case 4:
                        UserTextInterface.biblioteka.Show5MostCommonBooksInCategories();
                        break;
                    case 5:
                        UserTextInterface.biblioteka.Show5MostCommonAutors();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Niewłaściwy nr opcij z menu kontekstowaego");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("PODANA WARTOŚĆ NIE JEST LICZBĄ !!!");
                userInput.nextLine();
            }
        }
    }
}
