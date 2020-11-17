package cn.com.lichenghao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    public String index(@PathVariable("id") String id) {
        int a = 1 / 0;
        return id;
    }
}
