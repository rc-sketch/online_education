package com.oe.service;

import com.oe.domain.dto.TabStudentDTO;
import com.oe.domain.vo.TabStudentVO;
import com.oe.util.PageBean;

public interface StudentService {
    TabStudentDTO findStudentById(Long id);

    PageBean findStudentPage(Integer pageNumber, Integer pageSize, String name, String phone);

    void insertRegister(TabStudentDTO studentDTO);

    TabStudentDTO findStudentByName(String name);
}
