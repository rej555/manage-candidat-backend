package procheacksa.students.dio;

import org.springframework.data.jpa.repository.JpaRepository;
import procheacksa.students.entites.Candidateur;

public interface CandidatRepository extends JpaRepository<Candidateur,Long> {
}

