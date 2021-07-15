package com.spacey.springboot.course;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update CourseMaterial cm set cm.url = ?1 where cm.course = ?2")
//	int updateMaterialUrlForCourse(String url, Course course);
//
//	Optional<CourseMaterial> findByCourse(Course course);

}
