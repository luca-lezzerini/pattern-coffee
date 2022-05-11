package com.iad.statepattern01;

public class StatePattern01 {
    
    public static void main(String[] args) {
        Automaton a = new Automaton();
        
        a.next(new SoldiEvent(0.10));
        a.next(new CaffeEvent());
        a.next(new SoldiEvent(1.00));
        a.next(new CaffeEvent());
        a.next(new CaffeProntoEvent());
        a.next(new RitiratoEvent());
        a.next(new RestoEvent());
    }
}
