package pl.parser.nbp.controller;

import pl.parser.nbp.model.Utils;
import pl.parser.nbp.service.CurrencyService;

import java.util.Scanner;

public class CurrencyController {
    public void run() {
    String currencySymbol;
    String startDate;
    String endDate;
    String url;
    boolean flag = true;

    do {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj symbol waluty np. USD : ");
        currencySymbol = scanner.nextLine();
        if (currencySymbol.equals("e")) {
            break;
        }

        System.out.println("Podaj datę początkową np.2013-01-28 : ");
        startDate = scanner.nextLine();
        if (startDate.equals("e")) {
            break;
        }

        System.out.println("Podaj datę końcową np.2013-01-31: ");
        endDate = scanner.nextLine();
        if (endDate.equals("e")) {
            break;
        }
        try {
            CurrencyService currencyService = new CurrencyService();
            url = currencyService.createUrl(currencySymbol, startDate, endDate);

            Utils utils = new Utils();
            utils.redWebsiteContent(url);

            System.out.println();
            System.out.println("Podaj kolejną walutę lub zakończ wybierając ( e ).");
        } catch (NullPointerException e) {
            System.out.println("Nie ma takiej waluty lub data ma zly format.");
            System.out.println("Podaj kolejną walutę lub zakończ wybierając ( e ).");
        }
    } while (flag = true);
    System.out.println("Koniec programu.");
}
}
