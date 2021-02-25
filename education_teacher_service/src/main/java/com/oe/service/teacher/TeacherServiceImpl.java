package com.oe.service.teacher;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oe.domain.dto.TabTeacherDTO;
import com.oe.mapper.teacher.TeacherMapper;
import com.oe.pojo.TabTeacher;
import com.oe.service.TeacherService;
import com.oe.util.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service.teacher
 * @ClassName: TeacherServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 11:59
 * @Version: 1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    /***
     * 根据教师名称查询
     * @param name
     * @return
     */
    @Override
    public TabTeacherDTO findTeacherByName(String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        List<TabTeacher> tabTeachers = teacherMapper.selectByMap(map);
        TabTeacher teacher = tabTeachers.get(0);
        TabTeacherDTO tabTeacherDTO = new TabTeacherDTO();
        BeanUtils.copyProperties(teacher,tabTeacherDTO);
        return tabTeacherDTO;
    }

    /***
     * 根据教室名称模糊查询并分页
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageBean findTeacherPage(Integer pageNumber, Integer pageSize, String name,String levelName) {
        QueryWrapper<TabTeacher> queryWrapper = new QueryWrapper<TabTeacher>();
        // name 不为空查询
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        // 教师等级
        queryWrapper.like(StringUtils.isNotBlank(levelName), "level_name", levelName);
        // 第一个参数为当前页(pageNum),第二个参数为每页记录数(pageSize)
        Page<TabTeacher> userPage = new Page<>(pageNumber, pageSize);
        IPage<TabTeacher> userIPage = teacherMapper.selectPage(userPage, queryWrapper);
        // 将Pojo转成VO
        List<TabTeacher> list = userIPage.getRecords();
        List<TabTeacherDTO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TabTeacherDTO tabTeacherVO = new TabTeacherDTO();
            BeanUtils.copyProperties(list.get(i),tabTeacherVO);
            list1.add(tabTeacherVO);
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

    /***
     * 删除教师信息
     * @param teacherId
     */
    @Override
    public void deleteTeacherByTeacherId(String teacherId) {
        teacherMapper.deleteById(teacherId);
    }

    /***
     * 增加教师信息
     * @param teacherDTO
     */
    @Override
    public void insertTeacher(TabTeacherDTO teacherDTO) {
        TabTeacher teacher = new TabTeacher();
        BeanUtils.copyProperties(teacherDTO,teacher);
        teacherMapper.insert(teacher);
    }

    /***
     * 修改教师信息(审核)
     * @param teacher
     */
    @Override
    public void updateTeacherByTeacherId(TabTeacherDTO teacher) {
        TabTeacher teacher1 = new TabTeacher();
        BeanUtils.copyProperties(teacher,teacher1);
        teacherMapper.updateById(teacher1);
    }

    /***
     * 根据主键查询对应教师信息
     * @param teacherId
     * @return
     */
    @Override
    public TabTeacherDTO findTeacherByTeacherId(String teacherId) {
        TabTeacher teacher = teacherMapper.selectById(teacherId);
        TabTeacherDTO tabTeacherDTO = new TabTeacherDTO();
        BeanUtils.copyProperties(teacher,tabTeacherDTO);
        return tabTeacherDTO;
    }
}
