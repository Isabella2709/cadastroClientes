package vel.com.cadastrocliente.cadastroClientes.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vel.com.cadastrocliente.cadastroClientes.dto.UserDto;
import vel.com.cadastrocliente.cadastroClientes.entity.User;
import vel.com.cadastrocliente.cadastroClientes.service.UserService;
import vel.com.cadastrocliente.cadastroClientes.service.UserServiceImpl;

import java.util.List;

@Controller
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/cadastrocliente")
   public String cadastroCliente(){

       return "cadastroCliente";
   }

   @GetMapping("/cadastrocliente")
    public String showRegistrationFrom(Model model){
       UserDto user = new UserDto();
       model.addText("user");
       return  "register";
   }

   @GetMapping("/users")
   public String users(Model model){
        List<UserDto> users = userServiceImpl.findAllUsers();
        model.addText("users");
        return  "users";
   }

   @PostMapping("/escolhaplano/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
       UserService userService = null;
       User existingUser = userService.findUserByEmail(userDto.getEmail());

       if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
           result.rejectValue("email", null,
                   "There is already an account registered with same email");
       }
        if(result.hasErrors()){
            Object UserDto = null;
            model.addText("user");
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?sucess";



   }



}
