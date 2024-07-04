package vel.com.cadastrocliente.cadastroClientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vel.com.cadastrocliente.cadastroClientes.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
