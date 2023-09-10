package kevin.trochon.resolveur.repository;

import kevin.trochon.resolveur.entity.TermesDuCalculBaoLoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CalculBaoLocRepository extends JpaRepository<TermesDuCalculBaoLoc, UUID> {
    Optional<TermesDuCalculBaoLoc> findByResultat(int resultat);
}
