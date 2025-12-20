import platform.Platform;
import platform.AuthService;
import platform.models.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Platform platform = new Platform();
        platform.loadData(); // load users & recipes from disk if present

        Scanner sc = new Scanner(System.in);
        AuthService auth = platform.getAuthService();

        System.out.println("=== Welcome to Vedic Bytes ===");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Browse recipes (no login)");
            System.out.println("4. Search recipes");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    auth.registerInteractive(sc);
                    break;
                case "2":
                    User user = auth.loginInteractive(sc);
                    if (user != null) {
                        platform.userMenu(sc, user);
                    }
                    break;
                case "3":
                    platform.browseRecipes(sc, null);
                    break;
                case "4":
                    platform.searchInteractive(sc, null);
                    break;
                case "5":
                    platform.saveData();
                    System.out.println("Data saved. Bye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
