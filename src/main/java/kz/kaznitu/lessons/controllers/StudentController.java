package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Student;
import kz.kaznitu.lessons.reposotories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.apache.coyote.http11.Constants.a;

@Controller
@RequestMapping(path = "/stud")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository ;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("student",new Student());
        return "student_add_form";
    }


    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student){
        studentRepository.save(student) ;
        return "redirect:/stud/all" ;
    }


    @GetMapping("/all2")
    public @ResponseBody Iterable<Student> allStudents(){
        return studentRepository.findAll() ;
    }

    @GetMapping("/all")
    public String allStudents2(Model model){
        List<Student> students = (List<Student>) studentRepository.findAll() ;
        model.addAttribute("students", students) ;
        return "students" ;
    }

    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        studentRepository.deleteById(idd);
        return new ModelAndView("redirect:/stud/all");

    }

    @PostMapping("/editStudd")
    public String editStud(@ModelAttribute Student student) {
        Student student1 = new Student();
        student1.setId(a);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setCourse(student.getCourse());
        studentRepository.save(student1);
        return "redirect:/stud/all2";
    }

    @RequestMapping(value = "/editStud",method = RequestMethod.GET)
    public ModelAndView editStud(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Student> student = (Optional<Student>) studentRepository.findById(id);
        model.addAttribute("student",student);
        return new ModelAndView("editStud");
    }
    @RequestMapping("/editStud")
    public String showForm2(Model model){
        model.addAttribute("student",new Student());
        return "editStud";
    }


}
