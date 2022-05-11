package com.iad.statepattern01;

public class Automaton implements State {

    private State stato;
    private double totale;

    public Automaton() {
        stato = new AttesaState();
    }

    @Override
    public void next(Event e) {
        System.out.println("\n\nSiamo nello stato " + stato + " e abbiamo ricevuto un evento " + e);
        stato.next(e);
        System.out.println("Dopo l'esecuzione di next siamo nello stato " + stato);

    }

    private class AttesaState implements State {

        @Override
        public void next(Event e) {
            if (e instanceof SoldiEvent) {
                totale += ((SoldiEvent) e).getImporto();
            } else if (e instanceof RestoEvent) {
                System.out.println("Ti restituisco € " + totale);
                totale = 0;
            } else if (e instanceof CaffeEvent) {
                if (totale >= 0.50) {
                    stato = new PreparazioneCaffèState();
                } else {
                    System.out.println("Credito insufficiente! Il caffè costa 0.50€");
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
