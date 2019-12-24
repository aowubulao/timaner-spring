package com.neoniou.timaner.controller;

import com.neoniou.timaner.pojo.User;
import com.neoniou.timaner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author neo.zzj
 * @date 2019-11-17
 */
@RestController
@RequestMapping("/api/timaner/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<User> queryUserById(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * 登录api
     * @param user 对象
     * @return 登录对象
     */
    @PostMapping
    public ResponseEntity<User> login(User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    /**
     * 异步请求检测邮箱有没有被注册
     * @param email 邮箱
     * @return true or false
     */
    @GetMapping("email/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.checkEmail(email));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.checkUsername(username));
    }

    /**
     * 注册
     * @param user 对象
     * @param checkCode 验证码
     * @return true or false
     */
    @PostMapping("register")
    public ResponseEntity<String> register(User user, @RequestParam("checkCode") String checkCode) {
        userService.register(user, checkCode);
        return ResponseEntity.ok().build();
    }

    /**
     * 重置密码
     * @param user 对象
     * @param checkCode 验证码
     * @return true or false
     */
    @PutMapping
    public ResponseEntity<String> resetPassword(User user, @RequestParam("checkCode") String checkCode) {
        userService.resetPassword(user, checkCode);
        return ResponseEntity.ok().build();
    }
}
