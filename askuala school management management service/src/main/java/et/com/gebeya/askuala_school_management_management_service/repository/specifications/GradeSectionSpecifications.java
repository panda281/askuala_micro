package et.com.gebeya.askuala_school_management_management_service.repository.specifications;


import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import et.com.gebeya.askuala_school_management_management_service.model.GradeSection;
import et.com.gebeya.askuala_school_management_management_service.model.Teacher;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class GradeSectionSpecifications {
    public static Specification<GradeSection> GradeSectionBYHomeRoomTeacherId(int teacherId){
        return(root,cq,cb)->cb.equal(root.get("home_room_teacher_id"),teacherId);
    }


    public static Specification<GradeSection> getGradeSectionById(int id){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"),id);
    }

    public static Specification<GradeSection> getGradeSectionByGradeAndSection(Grade grade, Section section) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("grade"), grade),
                        criteriaBuilder.equal(root.get("section"), section)
                );
    }

    private static Join<GradeSection, Teacher> getGradeSectionTeacherJoin(Root<GradeSection> root) {
        return root.join("homeRoomTeacher");
    }
    public static Specification<GradeSection> GradeSectionByTeacherFirstName(String name){
        return (root,cq,cb)->{
            Join<GradeSection,Teacher>  studentGradeSection = getGradeSectionTeacherJoin(root);
            return cb.like(studentGradeSection.get("firstName"),"%"+name+"%");
        };
    }
    public static Specification<GradeSection> GradeSectionByTeacherId(int id){
        return (root,cq,cb)->{
            Join<GradeSection,Teacher>  studentGradeSection = getGradeSectionTeacherJoin(root);
            return cb.equal(studentGradeSection.get("id"),id);
        };
    }

    public static Specification<GradeSection> getAllGradeSection()
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("isActive"),false)));
    }


    public static Specification<GradeSection> getGradeSectionByGrade(Grade grade)
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("grade"),grade)));
    }


    public static Specification<GradeSection> getGradeSectionBySection(Section section)
    {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("section"),section)));
    }




}
