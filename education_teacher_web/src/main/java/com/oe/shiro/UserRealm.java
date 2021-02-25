package com.oe.shiro;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {//继承Authorin... 用来连接自己得数据库

    @Autowired
    private TeacherFeign teacherFeign;
   //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权方法");
        return null;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证方法");

        //从登录传来的token
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        DataResult<TabTeacherVO> name = teacherFeign.findTeacherByName(token.getUsername());
        TabTeacherVO teacher = name.getData();
        //判断用户名  查数据库 shiro自动校验
        if (teacher == null){
            return null;
        }
        //判断密码  shiro自动校验 中间参数是密码 数据库查询出来的密码
        //1.登录用户对象 2.数据库密文密码 3.登录用户的盐 4.登录用户真是名称
        return new SimpleAuthenticationInfo(teacher,teacher.getPassword(), ByteSource.Util.bytes(teacher.getSalt()),teacher.getName());
    }
}
