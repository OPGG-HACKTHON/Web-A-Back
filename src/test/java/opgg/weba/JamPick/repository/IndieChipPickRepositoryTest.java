package opgg.weba.JamPick.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class IndieChipPickRepositoryTest {

    @Autowired EntityManager em;
}
