package com.emmairving.bob.api.controller;

import com.emmairving.bob.api.common.ControllerResult;
import com.emmairving.bob.api.model.User;
import com.emmairving.bob.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by irving on 17/2/6.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(
        value = "增加用户",
        notes = "新建用户",
        response = ControllerResult.class,
        produces = "application/json"
    )
    @RequestMapping(method = RequestMethod.POST, value="user")
    public ControllerResult insertUser(
            @ApiParam(value = "用户名", required = true)
            @RequestParam(name = "name", required = false)
            String name,

            @ApiParam(value = "密码", required = true)
            @RequestParam(name = "password", required = false)
            String password,

            @ApiParam(value = "电表号", required = false)
            @RequestParam(name = "meter_number", required = false)
            String meter_number
    ) {
        ControllerResult rst = new ControllerResult();

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setMeter_number(meter_number);

        try {
            userService.insert(user);
            rst.setResultCode("1");
        } catch(Exception e) {
            e.printStackTrace();
            rst.setResultCode("2");
        }

        return rst;
    }
}
