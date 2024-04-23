package ua.edu.nuos.lecture2304.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.nuos.lecture2304.service.StudentService;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/show")
    public String showAllStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @GetMapping("/add")
    public String addStudent() {

        return "add_student";
    }

    @PostMapping("/addstudent")
    public String addStudent(@RequestParam int id, @RequestParam String name, @RequestParam int age, @RequestParam double rating) {
        studentService.addStudent(id, name, age, rating);
        return "redirect:/show";
    }

    @GetMapping("/delete_student")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
        return "redirect:/show";
    }

}
