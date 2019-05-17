package pl.parser.nbp;

import pl.parser.nbp.controller.CurrencyController;
import pl.parser.nbp.view.GUI;

public class MainClass {

    public static void main(String[] args) {
        new GUI().showGui();
        new CurrencyController().run();
    }
}

