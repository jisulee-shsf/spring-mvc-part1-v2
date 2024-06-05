package study.springmvcpart1v2.basic.mapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String findUsers() {
        return "findUsers";
    }

    @PostMapping
    public String addUser() {
        return "addUser";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "findUser = " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "updateUser = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "deleteUser = " + userId;
    }
}
