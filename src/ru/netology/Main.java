package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "Georgy", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> person = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            person.add(new Person(names.get(new Random().nextInt(names.size())), families.get(new Random().nextInt(names.size())), new Random().nextInt(100), Sex.values()[new Random().nextInt(Sex.values().length)], Education.values()[new Random().nextInt(Education.values().length)]));
        }

        long yung = person.stream()
                .filter(q -> q.getAge() < 18)
                .count();
        System.out.println("Количество людей младше 18 лет: " + yung);

        List<String> military = person.stream()
                .filter((q) -> q.getAge() >= 18 && q.getAge() <= 27 && q.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список призывников: " + military);

        List<Person> worker = person.stream()
                .filter(q -> q.getAge() >= 18)
                .filter(q -> q.getEducation() == Education.HIGHER)
                .filter((q) -> (q.getSex() == Sex.WOMAN && q.getAge() <= 65) || (q.getSex() == Sex.MAN && q.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Список работоспособного населения: " + worker);
    }
}
