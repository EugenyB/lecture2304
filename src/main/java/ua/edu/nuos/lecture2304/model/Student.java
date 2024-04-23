package ua.edu.nuos.lecture2304.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
    private final int id;
    private String name;
    private int age;
    private double rating;
}
