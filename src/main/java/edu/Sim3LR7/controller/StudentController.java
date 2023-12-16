package edu.Sim3LR7.controller;


import edu.Sim3LR7.dao.StudentsRepository;
import edu.Sim3LR7.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class StudentController {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping({"/list","/"})
    public ModelAndView getAllStudents(){
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("List-students");
        mav.addObject("students",studentsRepository.findAll());
        return mav;
    }

    @GetMapping({"/addStudentForm"})
    public ModelAndView addStudentForm(){
        ModelAndView mav = new ModelAndView("add-student-form");
        Student student = new Student();
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student){
        studentsRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long studentId) {
        ModelAndView mav = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentsRepository.findById(studentId);
        Student student = new Student();
        if (optionalStudent.isPresent()){
            student = optionalStudent.get();
        }
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId){
        studentsRepository.deleteById(studentId);
        return "redirect:/list";
    }
}
