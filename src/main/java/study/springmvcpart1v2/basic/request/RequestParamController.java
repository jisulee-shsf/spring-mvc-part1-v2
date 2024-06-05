package study.springmvcpart1v2.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String MemberName, @RequestParam("age") int MemberAge) {
        log.info("MemberName={}, MemberAge={}", MemberName, MemberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //http://localhost:8080/request-param-required-v1?age=20
    @ResponseBody
    @RequestMapping("/request-param-required-v1")
    public String requestParamRequiredV1(@RequestParam(required = false) String username,
                                         @RequestParam(required = true) int age) {
        log.info("username={}, age={}", username, age); //username=null, age=20
        return "ok";
    }

    //http://localhost:8080/request-param-required-v2?username=hello
    @ResponseBody
    @RequestMapping("/request-param-required-v2")
    public String requestParamRequiredV2(@RequestParam(required = true) String username,
                                         @RequestParam(required = false) int age) {
        log.info("username={}, age={}", username, age); //IllegalStateException
        return "ok";
    }

    //http://localhost:8080/request-param-required-v3?username=hello
    @ResponseBody
    @RequestMapping("/request-param-required-v3")
    public String requestParamRequiredV3(@RequestParam(required = true) String username,
                                         @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age); //username=hello, age=null
        return "ok";
    }

    //http://localhost:8080/request-param-required-v4?username=&age=20
    @ResponseBody
    @RequestMapping("/request-param-required-v4")
    public String requestParamRequiredV4(@RequestParam(required = true) String username,
                                         @RequestParam(required = true) Integer age) {
        log.info("username={}, age={}", username, age); //username=, age=20
        return "ok";
    }

    //http://localhost:8080/request-param-default?username=hello -> username=hello, age=123
    //http://localhost:8080/request-param-default?username= -> username=abc, age=123
    //http://localhost:8080/request-param-default -> username=abc, age=123
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "abc") String username,
                                      @RequestParam(defaultValue = "123") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
