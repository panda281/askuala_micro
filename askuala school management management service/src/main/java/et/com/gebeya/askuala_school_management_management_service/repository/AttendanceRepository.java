package et.com.gebeya.askuala_school_management_management_service.repository;


import et.com.gebeya.askuala_school_management_management_service.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer >, JpaSpecificationExecutor<Attendance> {

    @Query(nativeQuery = true,value = "select attendance.attendance_date,attendance.remark,attendance.status,s.student_id," +
            "s.first_name,s.middle_name,s.last_name from attendance inner join student s on s.id = attendance.student_id " +
            "inner join grade_section gs on s.grade_section_id = gs.id and s.is_active = true and gs.home_room_teacher_id = :id")
    List<Object[]>getAllAttendanceByTeacherId(Integer id);


    @Query(nativeQuery = true, value = "select attendance.attendance_date,attendance.remark,attendance.status from attendance " +
            "inner join student s on s.id = attendance.student_id inner join grade_section gs on s.grade_section_id = gs.id and" +
            " s.is_active = true and s.id = :id")
    List<Object[]>getAllAttendanceByStudentId(Integer id);





}
