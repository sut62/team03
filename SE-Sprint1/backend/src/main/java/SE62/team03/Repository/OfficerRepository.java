package SE62.team03.Repository;
import SE62.team03.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

@RepositoryRestResource
public
interface OfficerRepository extends JpaRepository<Officer, Long> {
    Officer findById(long id);
    Officer findOfficerById(Long OfficerId);

// ================================= เช็ค officer --> login ===============================================
    @Query( value = "SELECT * FROM Officer z Where z.username = :username and z.password = :password",
    nativeQuery = true)
    Officer findCheck(@Param("username") String username,@Param("password") String password);
}
