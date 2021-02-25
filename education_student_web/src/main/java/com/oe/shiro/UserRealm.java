package com.oe.shiro;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabStudentVO;
import com.oe.feign.rc.StudentFeign;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class UserRealm extends AuthorizingRealm {//继承Authorin... 用来连接自己得数据库

    @Autowired
    private StudentFeign studentFeign;
   //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权方法");
        //给资源进行授权
        //SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串 与perms内需要设置权限字符串对应  //两种方法
        //方法二：定义集合
//        List<String> perms=new ArrayList<>();
//        perms.add("add");
//        perms.add("update");
//        perms.add("excel");
//        info.addStringPermissions(perms);
        //方法一：一个一个添加
       /* info.addStringPermission("add");
        info.addStringPermission("update");*/
        return null;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证方法");
        //从登录传来的token
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        DataResult<TabStudentVO> studentByName = studentFeign.findStudentByName(username);
        TabStudentVO student = studentByName.getData();
        //判断用户名  查数据库 shiro自动校验
        if (student == null){
            return null;
        }
        //判断密码  shiro自动校验 中间参数是密码 数据库查询出来的密码
        //1.登录用户对象 2.数据库密文密码 3.登录用户的盐 4.登录用户真是名称
        return new SimpleAuthenticationInfo(student,student.getPassword(), ByteSource.Util.bytes(student.getSalt()),"");
    }
}
