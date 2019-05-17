package pl.parser.nbp.service;

import pl.parser.nbp.model.Rate;

import java.util.List;

public class CurrencyService implements CurrencyServiceInterface{
    public String createUrl(String currencySymbol, String startDate, String endDate) {
        //API adress+curency symbol + start date + end date
        String url = "http://api.nbp.pl/api/exchangerates/rates/c/" + currencySymbol + "/" + startDate + "/" + endDate;
        return url;
    }

    //calculation of Bid avverage
    public Double calculateAverageBid(List<Rate> rates) {
        Double averageBid = 0.00;
        for (Rate i : rates) {
            averageBid += i.getBid();
        }
        return averageBid / rates.size();
    }

    // calculation of Ask standard deviation, to calculation need Ask average
    public Double calculateStandardDeviationAsk(List<Rate> rates) {
        //calculation of Ask average
        Double averageAsk = 0.00;
        Double avg = 0.00;
        for (Rate i : rates) {
            averageAsk += i.getAsk();
        }
        avg = averageAsk / rates.size();

        // calculation of Ask standard deviation
        Double variance = 0.00;
        for (Rate i : rates) {
            variance += (i.getAsk() - avg) * (i.getAsk() - avg);
        }
        variance = variance / (rates.size());

        return Math.sqrt(variance);
    }
}



