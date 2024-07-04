package vel.com.cadastrocliente.cadastroClientes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

   private Long id;
   @NotEmpty(message = "")
   private String proprietario;
   @NotEmpty(message = "Email should not be empty")
   @Email
   private String email;
   @NotEmpty(message = "Password sholud not be empty")
   private String password;

   public String setFirstName(String name) {
      this.proprietario = name;
      return name;
   }

   public void setLastName(String s) {
      this.proprietario = s;
   }

   public String getFirstName() {
      return proprietario;
   }
}
