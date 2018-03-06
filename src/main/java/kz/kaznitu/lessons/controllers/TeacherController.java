package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Student;
import kz.kaznitu.lessons.models.Teacher;
import kz.kaznitu.lessons.reposotories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository ;
    private long a;
    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("teacher",new Teacher());
        return "teacher_add_form";
    }


    @PostMapping("/add")
    public String addTeacher(@ModelAttribute Teacher teacher){
        teacherRepository.save(teacher) ;
        return "redirect:/demo/all" ;
    }


    @GetMapping("/all2")
    public @ResponseBody Iterable<Teacher> allTeachers(){
        return teacherRepository.findAll() ;
    }

    @GetMapping("/all")
    public String allTeachers2(Model model){
        List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll() ;
        model.addAttribute("teachers", teachers) ;
        return "teachers" ;
    }

    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        teacherRepository.deleteById(idd);
        return new ModelAndView("redirect:/demo/all");
    }
    @PostMapping("/editTeacherr")
    public String editTeacher(@ModelAttribute Teacher teacher) {
        Teacher teacher1 = new Teacher();
        teacher1.setId(a);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        teacher1.setEmail(teacher.getEmail());
        teacherRepository.save(teacher1);
        return "redirect:/demo/all2";
    }

    @RequestMapping(value = "/editTeacher",method = RequestMethod.GET)
    public ModelAndView editTeacher(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Teacher> teacher = (Optional<Teacher>) teacherRepository.findById(id);
        model.addAttribute("teacher",teacher);
        return new ModelAndView("editTeach");
    }
    @RequestMapping("/editTeach")
    public String showForm2(Model model){
        model.addAttribute("teacher",new Teacher());
        return "editTeach";
    }




}
