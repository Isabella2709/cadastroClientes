package vel.com.cadastrocliente.cadastroClientes.service;

import vel.com.cadastrocliente.cadastroClientes.dto.UserDto;
import vel.com.cadastrocliente.cadastroClientes.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
