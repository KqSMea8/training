package seker.pattern07.adapter;

public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Job adapter1 = new Adapter1();
        System.out.println("Class apdater mode:");
        adapter1.speakEnglish();
        adapter1.speakJapanese();
        adapter1.speakFrench();
        
        System.out.println("\nInstance apdater mode:");
        Person person = new Person();
        Job adapter2 = new Adapter2(person);
        adapter2.speakEnglish();
        adapter2.speakJapanese();
        adapter2.speakFrench();
    }

}
