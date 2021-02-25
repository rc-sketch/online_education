package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabTeacherDTO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.pojo.TabTeacher;
import com.oe.service.TeacherService;
import com.oe.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.controller
 * @ClassName: TeacherController
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 11:56
 * @Version: 1.0
 */
@RestController
@RequestMapping("teacher")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "教师服务")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

//    @Resource
//    private TeacherFeign teacherFeign;

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    /***
     * 根据主键查询对应教师信息
     * @param teacherId
     * @return
     */
    @GetMapping("/{teacherId}")
    @ApiOperation(value = "根据主键查询教师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", required = true,
                    paramType = "path", dataTypeClass = String.class),
    })
    public DataResult<TabTeacherVO> findTeacherByTeacherId(@PathVariable("teacherId") String teacherId){
        try {
            // 根据id查询
            TabTeacherDTO teacherByTeacherId = teacherService.findTeacherByTeacherId(teacherId);
            // 创建VO实体
            TabTeacherVO teacher = new TabTeacherVO();
            // 将DTO转成VO
            BeanUtils.copyProperties(teacherByTeacherId,teacher);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacher);
        }catch (Exception e){
            logger.error("教师接口 根据主键获取教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据主键查询对应教师信息
     * @param teacherId
     * @return
     */
    @PutMapping("/{teacherId}")
    @ApiOperation(value = "根据主键修改教师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", required = true,
                    paramType = "path", dataTypeClass = String.class),
            @ApiImplicitParam(name = "teacherVO", value = "教师实体对象", required = true,
                     dataTypeClass = TabTeacherVO.class),
    })
    public DataResult updateTeacherByTeacherId(@PathVariable("teacherId") String teacherId,TabTeacherVO teacherVO){
        try {
            teacherVO.setTeacherId(teacherId);
            // 创建实体对象DTO
            TabTeacherDTO teacher = new TabTeacherDTO();
            BeanUtils.copyProperties(teacherVO,teacher);
            // 修改教师信息
            teacherService.updateTeacherByTeacherId(teacher);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("教师接口 根据主键修改教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }


    /***
     * 增加教师信息
     * @tabTeacherVO
     * @return
     */
    @PostMapping("/insertTeacher")
    @ApiOperation(value = "增加教师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherVO", value = "教师实体对象", required = true,
                    dataTypeClass = TabTeacherVO.class),
    })
    public DataResult insertTeacher(TabTeacherVO teacherVO){
        try {
            // 创建实体对象DTO
            TabTeacherDTO teacher = new TabTeacherDTO();
            BeanUtils.copyProperties(teacherVO,teacher);
            // 修改教师信息
            teacherService.insertTeacher(teacher);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("教师接口 增加教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据主键删除对应教师信息
     * @param teacherId
     * @return
     */
    @DeleteMapping("/{teacherId}")
    @ApiOperation(value = "根据主键删除教师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", required = true,
                    paramType = "path", dataTypeClass = String.class),
    })
    public DataResult<TabTeacherVO> deleteTeacherByTeacherId(@PathVariable("teacherId") String teacherId){
        try {
             teacherService.deleteTeacherByTeacherId(teacherId);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("教师接口 根据主键删除教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 查询教师信息并分页
     * @return
     */
    @GetMapping("/findTeacherPage")
    @ApiOperation(value = "查询教师信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数", required = true,
                    dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true,
                     dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "教师名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "levelName", value = "教师等级", dataTypeClass = String.class),
    })
    public DataResult<TabTeacherVO> findTeacherPage(@RequestParam(defaultValue = "1")Integer pageNumber,@RequestParam(defaultValue = "3")Integer pageSize,String name,String levelName){
        try {
            PageBean page = teacherService.findTeacherPage(pageNumber,pageSize,name,levelName);
            List<TabTeacherDTO> result = page.getResult();
            List<TabTeacherVO> list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                TabTeacherVO tabTeacherVO = new TabTeacherVO();
                BeanUtils.copyProperties(result.get(i),tabTeacherVO);
                list.add(tabTeacherVO);
            }
            page.setResult(list);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(page);
        }catch (Exception e){
            logger.error("教师接口 根据主键获取教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据教师名称查询
     * @return
     */
    @GetMapping("/findTeacherByName/{name}")
    @ApiOperation(value = "根据教师名称查询(用户认证)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "教师名称", required = true,
                    paramType = "path",
                    dataTypeClass = String.class),
    })
    public DataResult<TabTeacherVO> findTeacherByName(@PathVariable("name") String name){
        try {
            TabTeacherDTO tabTeacherDTO = teacherService.findTeacherByName(name);
            TabTeacher teacher = new TabTeacher();
            BeanUtils.copyProperties(tabTeacherDTO,teacher);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacher);
        }catch (Exception e){
            logger.error("教师接口 根据主键获取教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }




//    测试Feign调用
//    @GetMapping("test")
//    public DataResult<TabTeacherVO> test(){
//        DataResult<TabTeacherVO> teacherByTeacherId = teacherFeign.findTeacherByTeacherId("101222");
//        return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacherByTeacherId.getData());
//    }

}
