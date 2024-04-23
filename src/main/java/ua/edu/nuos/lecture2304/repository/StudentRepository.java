package ua.edu.nuos.lecture2304.repository;

import org.springframework.stereotype.Service;
import ua.edu.nuos.lecture2304.model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRepository {

    public List<Student> findAll() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("students.txt"))) {
            return reader.lines()
                    .map(s -> s.split(";"))
                    .map(s->new Student(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Double.parseDouble(s[3]))).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Student student) {
        List<Student> students = new ArrayList<>(findAll());
        students.add(student);
        saveAll(students);
    }

    public void delete(Student student) {
        List<Student> students = new ArrayList<>(findAll());
        students.remove(student);
        saveAll(students);
    }

    private void saveAll(List<Student> students) {
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Path.of("students.txt")))) {
            for (Student student : students) {
                out.println(student.getId()+";"+student.getName()+";"+student.getAge()+";"+student.getRating());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Student> findById(int id) {
        return findAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    public void deleteById(int id) {
        List<Student> students = new ArrayList<>(findAll());
        students.removeIf(s -> s.getId() == id);
        saveAll(students);
    }
}
