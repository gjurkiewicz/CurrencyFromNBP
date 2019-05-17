package pl.parser.nbp.service;

import pl.parser.nbp.model.Rate;

import java.util.List;

public interface CurrencyServiceInterface {
    String createUrl(String currencySymbol, String startDate, String endDate);

    Double calculateAverageBid(List<Rate> rates);
    Double calculateStandardDeviationAsk(List<Rate> rates);
}

