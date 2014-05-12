package seker.pattern07.adapter;

public class Adapter2 implements Job {
    Person person;

    public Adapter2(Person person) {
        this.person = person;
    }

    @Override
    public void speakEnglish() {
        person.speakEnglish();
    }

    @Override
    public void speakJapanese() {
        person.speakJapanese();
    }

    @Override
    public void speakFrench() {
        System.out.println("Adapt to speak French!");
    }
}
