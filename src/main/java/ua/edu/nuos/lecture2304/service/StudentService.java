package ua.edu.nuos.lecture2304.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ua.edu.nuos.lecture2304.model.Student;
import ua.edu.nuos.lecture2304.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@AllArgsConstructor
public class StudentService {

    //private final List<Student> students = new ArrayList<>();

    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public boolean addStudent(int id, String name, int age, double rating) {
        if (studentRepository.findById(id).isPresent()) {
            return false;
        }
        Student student = new Student(id, name, age, rating);
        studentRepository.save(student);
        return true;
    }


    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
