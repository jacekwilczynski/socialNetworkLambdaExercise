
package exercise;

import exercise.Person.Gender;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class People implements Iterable<Person> {
    
    public Collection<Person> people = new HashSet<>();

    public People() {

    }

    public People(int numberToGenerate) {
        populate(numberToGenerate);
    }

    public final void populate(int numberToGenerate) {
        for (int i = 0; i < numberToGenerate; i++) {
            boolean added;
            do {
                Gender gender = randomGender();
                String name = randomName(gender);
                LocalDate birthday = randomDate();
                String emailAddress = generateEmail(name);
                added = people.add(new Person(name, birthday, gender, emailAddress));
            } while (!added);
        }
    }

    public void print() {
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Person person : people) {
            builder.append(person).append("\n");
        }
        return builder.toString();
    }

    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }
    
    private int randomInt(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

    private String pickRandom(String[] array) {
        return array[randomInt(0, array.length - 1)];
    }

    private String randomName(Gender gender) {
        final String[][] firstNames = {
            {"Michael", "George", "Jules", "Arnold", "Terrence", "Louis", "Samuel", "Nate", "Zak", "Donald", "Clint"},
            {"Amanda", "Jennifer", "Susan", "Nancy", "Deborah", "Theresa", "Barbara", "Hillary", "Patricia", "Melanie"}
        };
        final String[] lastNames = {"Jordan", "Clinton", "Trumpet", "Eastwood", "Smith", "Walker", "Hamilton", "Fry", "Mason"};

        int genderInt = gender.ordinal();
        String firstName = pickRandom(firstNames[genderInt]);
        String lastName = pickRandom(lastNames);
        return firstName + " " + lastName;
    }

    private int numberOfDays(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return Year.isLeap(year) ? 29 : 28;
            default:
                return 0;
        }
    }

    private LocalDate randomDate() {
        int year = randomInt(1916, 2016);
        int month = randomInt(1, 12);
        int day = randomInt(1, numberOfDays(month, year));
        return LocalDate.of(year, month, day);
    }

    private Gender randomGender() {
        return randomInt(0, 1) == 0 ? Gender.MALE : Gender.FEMALE;
    }

    private String generateEmail(String name) {
        name = name.replaceAll("[\" \"]", "").toLowerCase();
        return name + "@provider.com";
    }
    
}
