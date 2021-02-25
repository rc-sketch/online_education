//package com.oe.feign.xmj;
//
//import com.oe.data.DataResult;
//import com.oe.domain.vo.TabStudentVO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(value = "education-student-service")
//public interface StudentFeign {
//    /***
//     * 根据学生名称查询  登录认证
//     * @param name
//     * @return
//     */
//    @GetMapping("/teacher/findTeacherByName/{name}")
//    DataResult<TabStudentVO> findStudentByName(@PathVariable("name") String name);
//}
