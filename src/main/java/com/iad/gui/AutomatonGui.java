package com.iad.gui;

import com.iad.statepattern01.*;

public class AutomatonGui implements State {

    private State stato;
    private double totale;
    private CompagnoDellAutoma gui;

    public AutomatonGui(CompagnoDellAutoma gui) {
        stato = new AttesaState();
        this.gui = gui;
    }

    @Override
    public void next(Event e) {
        System.out.println("\n\nSiamo nello stato " + stato + " e abbiamo ricevuto un evento " + e);
        stato.next(e);
        System.out.println("Dopo l'esecuzione di next siamo nello stato " + stato);

    }

    private void visualizzaTotale() {
        String s = "Importo inserito €" + totale;
        gui.mostraMessaggio(s);
    }

    private class AttesaState implements State {

        @Override
        public void next(Event e) {
            if (e instanceof SoldiEvent) {
                totale += ((SoldiEvent) e).getImporto();
                visualizzaTotale();
            } else if (e instanceof RestoEvent) {
                String s = "Ti restituisco € " + totale;
                System.out.println(s);
                gui.mostraMessaggio(s);
                totale = 0;
            } else if (e instanceof CaffeEvent) {
                if (totale >= 0.50) {
                    stato = new PreparazioneCaffèState();
                } else {
                    String s = "Credito insufficiente! Il caffè costa 0.50€";
                    System.out.println(s);
                    gui.mostraMessaggio(s);
                }
            } else {
                System.out.println("Evento " + e + " inatteso!");
            }
        }

        @Override
        public String toString() {
            return "AttesaState{" + "totale = " + totale + '}';
        }

    }

    private class PreparazioneCaffèState implements State {

        @Override
        public void next(Event e) {
            if (e instanceof CaffeProntoEvent) {
                totale -= 0.50;
                visualizzaTotale();
                stato = new DaRitirareState();
            } else {
                System.out.println("Evento " + e + " inatteso!");
            }
        }

        @Override
        public String toString() {
            return "PreparazioneCaff\u00e8State{" + "totale = " + totale + '}';
        }

    }

    private class DaRitirareState implements State {

        @Override
        public void next(Event e) {
            if (e instanceof RitiratoEvent) {
                stato = new AttesaState();
            }
        }

        @Override
        public String toString() {
            return "DaRitirareState{" + "totale = " + totale + '}';
        }

    }
}
