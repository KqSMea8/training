package seker.pattern07.adapter;

public class Person {

    private String mName;
    private String mSex;
    private int mAge;

    public void speakJapanese() {
        System.out.println("I can speak Japanese!");
    }

    public void speakEnglish() {
        System.out.println("I can speak English!");
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }
}
