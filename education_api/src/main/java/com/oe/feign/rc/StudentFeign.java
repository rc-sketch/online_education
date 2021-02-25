package com.oe.feign.rc;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabStudentVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.util.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "education-student-service")
public interface StudentFeign {

    /***
     * 根据学生名称查询  登录认证
     * @param name
     * @return
     */
    @GetMapping("/student/findStudentByName/{name}")
    DataResult<TabStudentVO> findStudentByName(@PathVariable("name") String name);

    /**
     * 用户注册方法
     * @param studentVO
     * @param newpassword
     * @param code
     * @return
     */
    @PostMapping("/student/insertRegister")
    DataResult insertRegister(@RequestBody TabStudentVO studentVO, @RequestParam("newpassword") String newpassword, @RequestParam("code") String code);
    /***
     * 根据主键查询学生信息
     * @param id
     * @return
     */
    @GetMapping("/student/{id}")
    DataResult<TabStudentVO> findStudentById(@PathVariable("id") String id);

    /***
     * 查询教师信息并分页
     * @param pageNumber
     * @param pageSize
     * @param name
     * @param phone
     * @return
     */
    @GetMapping("/student/findStudentPage")
    DataResult<PageBean> findStudentPage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize, @RequestParam String name, @RequestParam String phone);
}
