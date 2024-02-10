package et.com.gebeya.askuala_school_management_management_service.repository.specifications;


import et.com.gebeya.askuala_school_management_management_service.model.Attendance;
import et.com.gebeya.askuala_school_management_management_service.model.Student;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class AttendanceSpecifications {
    public static Specification<Attendance> todayAttendance(Date date){
        return(root,cq,cb)->cb.greaterThanOrEqualTo(root.get("attendanceDate"),date);
    }

    public static Specification<Attendance> attendanceBetweenRange(Date start, Date end){
        return(root,cq,cb)->cb.between(root.get("attendanceDate"),start,end);
    }

    public static Specification<Attendance> AllPast(Date end){
        return (root,cq,cb)->cb.lessThanOrEqualTo(root.get("attendanceDate"),end);
    }

    public static Specification<Attendance> searchByStudentId(int id)
    {

        return ((root, query, criteriaBuilder) -> {
            Join<Attendance, Student> attendanceStudentJoin =getAttendanceStudentJoin(root);
            return criteriaBuilder.equal(attendanceStudentJoin.get("id"),id);
        });
    }

    private static Join<Attendance, Student> getAttendanceStudentJoin(Root<Attendance> root) {
        return root.join("student");
    }

    public static Specification<Attendance> getAllAttendance()
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false)));
    }



//    public static Specification<Attendance> getAttendanceByTeacherId(Integer id) {
//        return (root, query, criteriaBuilder) -> {
//            Join<Attendance, Student> studentJoin = root.join("student");
//            Join<Student, GradeSection> gradeSectionJoin = studentJoin.join("gradeSection");
//
//            Predicate isActivePredicate = criteriaBuilder.isTrue(studentJoin.get("isActive"));
//            Predicate teacherIdPredicate = criteriaBuilder.equal(gradeSectionJoin.get("homeRoomTeacher").get("id"), id);
//
//            return criteriaBuilder.and(isActivePredicate, teacherIdPredicate);
//        };
//    }



}
