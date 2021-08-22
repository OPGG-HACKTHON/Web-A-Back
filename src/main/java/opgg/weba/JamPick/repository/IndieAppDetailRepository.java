package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.IndieAppDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IndieAppDetailRepository extends JpaRepository<IndieAppDetail, Long> {
    Optional<IndieAppDetail> findByIndieApp_IdAndLanguage(@Param(value = "indieAppId") Long indieAppId, String language);
}
