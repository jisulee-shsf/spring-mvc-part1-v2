package study.springmvcpart1v2.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("body={}", body);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("body={}", body);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody();
        log.info("body={}", body);
        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(RequestEntity<String> requestEntity) {
        String body = requestEntity.getBody();
        log.info("body={}", body);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyStringV5(@RequestBody String body) {
        log.info("body={}", body);
        return "ok";
    }
}
