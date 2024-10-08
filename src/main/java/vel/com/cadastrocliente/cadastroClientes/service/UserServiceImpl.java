package vel.com.cadastrocliente.cadastroClientes.service;

import org.springframework.stereotype.Service;
import vel.com.cadastrocliente.cadastroClientes.dto.UserDto;
import vel.com.cadastrocliente.cadastroClientes.entity.Role;
import vel.com.cadastrocliente.cadastroClientes.entity.User;
import vel.com.cadastrocliente.cadastroClientes.repository.RoleRepository;
import vel.com.cadastrocliente.cadastroClientes.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public <PasswordEncoder> UserServiceImpl(UserRepository userRepository,
                                             RoleRepository roleRepository,
                                             PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = (vel.com.cadastrocliente.cadastroClientes.service.PasswordEncoder) passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFisrtName(userDto.getFirstName() + " ");
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role != null){
            role = checkRoleExist();
        }

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public  User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }


    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setEmail(user.getEmail());
        return userDto;

    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setFirstName("ROLE_ADIMIN");
        return roleRepository.save(role);
    }


}
