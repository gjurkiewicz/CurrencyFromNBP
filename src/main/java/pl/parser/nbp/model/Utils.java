package pl.parser.nbp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import pl.parser.nbp.service.CurrencyService;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    //red NBP API and save response as Json
    public static void redWebsiteContent(String url) {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(url);

            ClientResponse webResponse = webResource.accept("appliation/json").get(ClientResponse.class);

            if (webResponse.getStatus() != 200) {
                throw new RuntimeException("Błąd HTTP..." + webResponse.getStatus() + " - nie ma takiej waluty lub data ma zly format.");
            }

            String currencyJson = webResponse.getEntity(String.class);

            // Jackson use
            ObjectMapper mapper = new ObjectMapper();
            Currency jsonToObject = mapper.readValue(currencyJson, Currency.class);
            List rate = new ArrayList();
            rate.addAll(jsonToObject.getRates());
            CurrencyService currencyService = new CurrencyService();

            System.out.println("Śreni kurs kupna to: " + currencyService.calculateAverageBid(rate).toString());
            System.out.println("Odchylenie standardowe sprzedaży to: " + currencyService.calculateStandardDeviationAsk(rate).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}