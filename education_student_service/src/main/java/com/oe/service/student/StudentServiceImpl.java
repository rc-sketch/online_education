package com.oe.service.student;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oe.domain.dto.TabStudentDTO;
import com.oe.domain.dto.TabTeacherDTO;
import com.oe.domain.vo.TabStudentVO;
import com.oe.mapper.student.StudentMapper;
import com.oe.pojo.TabCourse;
import com.oe.pojo.TabStudent;
import com.oe.pojo.TabTeacher;
import com.oe.service.StudentService;
import com.oe.util.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName StudentServiceImpl
 * @Author RC
 * @date 2021.02.01 01:16
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    /***
     * 根据学生查询
     * @param id
     * @return
     */
    @Override
    public TabStudentDTO findStudentById(Long id) {
        TabStudent tabStudent = studentMapper.selectById(id);
        TabStudentDTO studentDTO = new TabStudentDTO();
        BeanUtils.copyProperties(tabStudent,studentDTO);
        return studentDTO;
    }

    /***
     * 根据学生名称/手机号模糊查询并分页
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageBean findStudentPage(Integer pageNumber, Integer pageSize, String name, String phone) {
        QueryWrapper<TabStudent> queryWrapper = new QueryWrapper<TabStudent>();
        // name 不为空查询
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        // 教师等级
        queryWrapper.like(StringUtils.isNotBlank(phone), "phone", phone);
        // 第一个参数为当前页(pageNum),第二个参数为每页记录数(pageSize)
        Page<TabStudent> userPage = new Page<>(pageNumber, pageSize);
        IPage<TabStudent> userIPage = studentMapper.selectPage(userPage, queryWrapper);
        // 将Pojo转成VO
        List<TabStudent> list = userIPage.getRecords();
        List<TabStudentDTO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TabStudentDTO tabStudentVO = new TabStudentDTO();
            BeanUtils.copyProperties(list.get(i),tabStudentVO);
            list1.add(tabStudentVO);
        }
        // 分页
        PageBean pageBean = new PageBean();
        pageBean.setResult(list1);
        pageBean.setPageNumber((int)userIPage.getCurrent());// 页数
        pageBean.setPageSize((int)userIPage.getSize());// 每页条数
        pageBean.setTotalCount((int)userIPage.getTotal());// 总条数
        pageBean.setTotalPage((int)userIPage.getPages());
        return pageBean;
    }

    @Override
    public void insertRegister(TabStudentDTO studentDTO) {
        TabStudent student = new TabStudent();
        BeanUtils.copyProperties(studentDTO, student);
        studentMapper.insert(student);
    }

    @Override
    public TabStudentDTO findStudentByName(String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        List<TabStudent> tabStudents = studentMapper.selectByMap(map);
        TabStudent student = tabStudents.get(0);
        TabStudentDTO studentDTO = new TabStudentDTO();
        BeanUtils.copyProperties(student,studentDTO);
        return studentDTO;
    }
}
