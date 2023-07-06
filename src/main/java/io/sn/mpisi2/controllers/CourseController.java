package io.sn.mpisi2.controllers;

import io.sn.mpisi2.models.Course;
import io.sn.mpisi2.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses = new ArrayList<>();

    //Afficher la liste des cours
    @GetMapping("")
    public String getCourses(Model model) {
        model.addAttribute("courses", courses);
        return "listCourse";
    }


    @PostMapping("/addCourse")
    public String addCourse(@RequestParam("id") int courseId,
                            @RequestParam("name") String name,
                            Model model) {
        // Ajouter le nouveau cours à la liste des cours
        Course course = new Course();
        course.setId(courseId);
        course.setName(name);
        course.setStudentsList(new ArrayList<>());
        courses.add(course);

        // Ajouter la liste mise à jour des cours au modèle
        model.addAttribute("courses", courses);

        return "listCourse";
    }

    //Afficher les etudiants inscrits a un cours donné en passant en paramettre id et student
    @GetMapping("/{courseId}/getCourse")
    public String getStudentsFromCourse(@PathVariable int courseId, Model model){
        //Recuperer la course dont Id est passe en paramettre avec la mthode getCourse
        Course course = getCourseById(courseId);
        if(course != null){
            model.addAttribute("course", course);
            return "courseStudents";
        }
        return "notFound";
    }

    //Ajouter un etudiant a un cours specifique
    @PostMapping("/{courseId}/addStudent")
    public String addStudentToCourse(@PathVariable int courseId,
                                     @RequestParam("id") int studentId,
                                     @RequestParam("name") String studentName,
                                     @RequestParam("grade") String studentGrade,
                                     @ModelAttribute Student student){
        Course course = getCourseById(courseId);
        student.setId(studentId);
        student.setName(studentName);
        student.setGrade(studentGrade);
        course.getStudentsList().add(student);

        return "listCourse";
    }

    @DeleteMapping("/{courseId}/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable("courseId") int courseId, @PathVariable("studentId") int studentId){
        Course course = getCourseById(courseId);
        if (course != null) {
            List<Student> studentsList = course.getStudentsList();
            Student studentToDelete = null;
            for (Student student : studentsList) {
                if (student.getId() == studentId) {
                    studentToDelete = student;
                    studentsList.remove(studentToDelete);
                    break;
                }
            }
        }
        return "listCourse";
    }


    //Methode getCourseById
    private Course getCourseById(int courseId){
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

}
