package com.oe.service;

import com.oe.domain.dto.TabTeacherDTO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.util.PageBean;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: TeacherService
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 11:58
 * @Version: 1.0
 */
// 教师端接口
public interface TeacherService {
    /***
     * 根据主键查询对应教师信息
     * @param teacherId
     * @return
     */
    TabTeacherDTO findTeacherByTeacherId(String teacherId);

    /***
     *修改教师信息(审核)
     * @param teacher
     */
    void updateTeacherByTeacherId(TabTeacherDTO teacher);

    /***
     * 增加教师信息
     * @param teacher
     */
    void insertTeacher(TabTeacherDTO teacher);

    /***
     * 删除教师信息
     * @param teacherId
     */
    void deleteTeacherByTeacherId(String teacherId);

    /***
     * 查询教师信息并分页
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    PageBean findTeacherPage(Integer pageNumber, Integer pageSize, String name,String levelName);

    /***
     * 根据教师名称查询
     * @param name
     * @return
     */
    TabTeacherDTO findTeacherByName(String name);
}
