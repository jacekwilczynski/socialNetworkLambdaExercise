package exercise;

import exercise.Person.Gender;

public class Test {

    public static void main(String[] args) {

        People people = new People(20);
        
        people.forEach(p -> {
            if (p.getGender() == Gender.FEMALE) {
                p.print();
            }
        });
        
    }

}
