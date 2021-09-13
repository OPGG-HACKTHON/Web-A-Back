package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.IndieApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndieAppRepository extends JpaRepository<IndieApp, Long> {

    @Query(
            value = "SELECT g.description FROM genre AS g" +
                    " WHERE g.language = :language AND g.indie_app_id = :id" +
                    " ORDER BY g.description",
            nativeQuery = true
    )
    List<String> findGenres(@Param("id") Long indieAppId, @Param("language") String language);

    @Query(
            value = "SELECT m.mp4 FROM movie AS m" +
                    " WHERE m.indie_app_id = :id" +
                    " ORDER BY m.mp4",
            nativeQuery = true
    )
    List<String> findMovies(@Param("id") Long indieAppId);

    @Query(
            value = "SELECT s.path_full FROM screenshot AS s" +
                    " WHERE s.indie_app_id = :id" +
                    " ORDER BY s.path_full",
            nativeQuery = true
    )
    List<String> findScreenshots(@Param("id") Long indieAppId);

    @Query("SELECT i.name from IndieApp i where i.id = :id")
    String findNameById(@Param("id") Long indieAppId);
}