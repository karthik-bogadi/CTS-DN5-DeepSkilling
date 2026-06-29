package org.karthik.studentmanagement_crud.repository;

import org.karthik.studentmanagement_crud.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    ArrayList<Student> students=new ArrayList<>();

    public List<Student> findAll(){
        return students;
    }
    public void save(Student student){
        students.add(student);
    }
    public void delete(int id){
        for(int i=0;i<students.size();i++){
            Student temp=students.get(i);
            int tempId= temp.getId();
            if(tempId==id){
                students.remove(i);
                break;
            }
        }
    }

    public void update(int id,Student student){
        for(int i=0;i<students.size();i++){
            int temp=students.get(i).getId();
            if(temp==id){
                students.get(i).setName(student.getName());
                students.get(i).setBranch(student.getBranch());
            }
        }
    }
}
