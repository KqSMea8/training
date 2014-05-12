package seker.pattern07.adapter;

public class Adapter1 extends Person implements Job {

    @Override
    public void speakFrench() {
        System.out.println("Adapt to speak French!");
    }

}
