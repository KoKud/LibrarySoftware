import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Book implements Serializable {
    static List<String> Categories;

    static {
        try {
            Categories = InitialCategories();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    List<String> ThisBookCategories = new LinkedList<>();
    int ID;
    String Title;
    String AutorFirstName;
    String AutorLastName;
    boolean IsRent;
    int RentCounter;
    int Year;

    public Book(String Title, String AutorFirstName, String AutorLastName, int Year) {
        this.Title = Title;
        this.AutorFirstName = AutorFirstName;
        this.AutorLastName = AutorLastName;
        this.Year = Year;
        this.IsRent = false;
        this.RentCounter = 0;
    }

    private static LinkedList InitialCategories() throws IOException, ClassNotFoundException {
        LinkedList Temp;// new LinkedList<>();
        File file = new File("category.bin");
        if (file.exists()) {
            try (ObjectInputStream fileCategory = new ObjectInputStream(new FileInputStream("category.bin"))) {
                Temp = (LinkedList) fileCategory.readObject();
            }
        } else {
            Temp = new LinkedList<>();
        }
        return Temp;
    }

    public static void AddCategories() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Podaj nazwe kategorii którą chcesz dodać. Jeśli chcesz zakończyć dodawanie kategori napisz \"EXIT\"");
        do {
            String tempCategory = userInput.nextLine();
            if (tempCategory.equalsIgnoreCase("EXIT")) break;
            Book.Categories.add(tempCategory);
        } while (true);
        File fileRepair = new File("category.bin");
        if (fileRepair.exists()) {
            System.out.println("Tworze backup poprzedniej bazy kategorii");
            Files.copy(Paths.get("category.bin"), Paths.get("category.old"), StandardCopyOption.REPLACE_EXISTING);
        }
        try (ObjectOutputStream fileCategorySave = new ObjectOutputStream(new FileOutputStream("category.bin"))) {
            fileCategorySave.writeObject(Book.Categories);
        }
    }

    public static void RemoveCategories() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Z poniższej listy wszystkich kategori napisz którą chcesz usunąć. Aby zakończyc kasowanie napisz\"EXIT\"");
        ScrollCategories();
        do {
            String tempCategory = userInput.nextLine();
            if (tempCategory.equalsIgnoreCase("EXIT")) break;
            Book.Categories.remove(tempCategory);
        } while (true);
        File fileRepair = new File("category.bin");
        if (fileRepair.exists()) {
            System.out.println("Tworze backup poprzedniej bazy kategorii");
            Files.copy(Paths.get("category.bin"), Paths.get("category.old"), StandardCopyOption.REPLACE_EXISTING);
        }
        try (ObjectOutputStream fileCategorySave = new ObjectOutputStream(new FileOutputStream("category.bin"))) {
            fileCategorySave.writeObject(Book.Categories);
        }
    }

    public static void ScrollCategories() {
        for (int i = 0; i < Book.Categories.size(); i++)
            System.out.print(" " + Book.Categories.get(i) + "; ");
        System.out.println();
    }
}
