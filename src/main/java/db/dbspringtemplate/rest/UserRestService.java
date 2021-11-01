package db.dbspringtemplate.rest;

import db.dbspringtemplate.dto.UserDto;
import db.dbspringtemplate.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestService extends AllowCorsService{

    private final UserService userService;

    @Autowired
    public UserRestService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os usuários")
    public List<UserDto> getAllTeams(@RequestParam(required = false) String name) {
        if (name == null)
            return userService.getAllUsers();
        return userService.getAllUsersByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca um usuario")
    public UserDto getTeam(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um usuario")
    public UserDto createTeam(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza um usuario")
    public UserDto updateTeam(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Inativa um usuario")
    public void inactiveTeam(@PathVariable Long id) {
        userService.inactiveUser(id);
    }
}
