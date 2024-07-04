package vel.com.cadastrocliente.cadastroClientes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vel.com.cadastrocliente.cadastroClientes.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
