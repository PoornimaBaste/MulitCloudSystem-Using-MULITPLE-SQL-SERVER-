package mysql.project.Services;

import lombok.NoArgsConstructor;
import mysql.project.DTO.LecturerDetails;
import mysql.project.DTO.StudentInfo;
import mysql.project.primaryEntity.Lecturer;
import mysql.project.rentedEntity.Marks;
import mysql.project.secondaryEntity.Student;
import mysql.project.primary.repo.PrimaryLecturerRepository;
import mysql.project.rented.repo.SecondaryRentMarksRepository;
import mysql.project.secondary.repo.SecondaryStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class StudentService {
    @Autowired
    private  SecondaryStudentRepository studentRepository;

    @Autowired
    private PrimaryLecturerRepository primaryLecturerRepository;

    @Autowired
    private SecondaryRentMarksRepository secondaryRentMarksRepository;

    public Optional<Student> validateStudent(String rollNo, String email){
        return studentRepository.findByRollNoAndEmail(rollNo,email);
    }

    public StudentInfo getStudentById(Integer studentId) {
        Student st= studentRepository.findById(studentId).orElseThrow(()->new IllegalArgumentException("Student not found!!"));

            StudentInfo studentInfo=new StudentInfo();
            studentInfo.setStudentId(studentId);
            studentInfo.setRollNo(st.getRollNo());
            studentInfo.setName(st.getName());
            studentInfo.setGender((st.getGender().equals("M"))?"Male":"Female");
            studentInfo.setSection(st.getStudentClass());
            studentInfo.setEmail(st.getEmail());
            studentInfo.setMarks(secondaryRentMarksRepository.findByStudentId(studentId));
            studentInfo.setLecturers(primaryLecturerRepository.findBySection(st.getStudentClass()));
            return studentInfo;

    }

}
