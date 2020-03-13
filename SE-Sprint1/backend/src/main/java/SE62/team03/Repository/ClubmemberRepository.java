package SE62.team03.Repository;
import SE62.team03.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ClubmemberRepository extends JpaRepository<Clubmember, Long> {
    Clubmember findById(long id);
}