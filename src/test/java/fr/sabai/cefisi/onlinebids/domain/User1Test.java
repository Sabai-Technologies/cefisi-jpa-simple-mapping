package fr.sabai.cefisi.onlinebids.domain;

import fr.sabai.cefisi.onlinebids.config.InitDBConfig;
import fr.sabai.cefisi.onlinebids.config.JpaTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, InitDBConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class User1Test {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void should_read_user_with_id_1() {
        // given
        long id = 1L;

        // when
        User1 user = entityManager.find(User1.class, id);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Rachel");
        assertThat(user.getLastName()).isEqualTo("Foster");
        assertThat(user.getEmail()).isEqualTo("dignissim@interdumSedauctor.co.uk");

        // address
        assertThat(user.getAddress()).isNotNull();
        assertThat(user.getAddress().getStreet()).isEqualToIgnoringCase("5346 Aliquam Road");
        assertThat(user.getAddress().getZipCode()).isEqualToIgnoringCase("57711");
        assertThat(user.getAddress().getCity()).isEqualToIgnoringCase("Castanhal");
    }


    @Test
    public void should_save_new_user() {
        // given
        User1 newUser = new User1();
        newUser.setFirstName("Luke");
        newUser.setLastName("Skywalker");
        newUser.setEmail("luke@skywalker.net");
        Address address = new Address();
        address.setCity("Tatooin");
        address.setZipCode("FLKD 2045");
        address.setStreet("Uncle's farm");
        newUser.setAddress(address);

        Integer rowBeforeSave = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_1", Integer.class);

        // when
        entityManager.persist(newUser);
        entityManager.flush();
        Integer rowAfterSave = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_1", Integer.class);

        // un autre query de jdbcTemplate pour aller plus loin dans la vérification
        // jdbcTemplate.query...

        // then
        assertThat(rowAfterSave).isEqualTo(rowBeforeSave + 1);
    }
}
