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
public class User3Test {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void should_read_user_with_id_1() {
        // given
        long id = 1L;

        // when
        User3 user = entityManager.find(User3.class, id);

        // then

        //User
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Ivana");
        assertThat(user.getLastName()).isEqualTo("Floyd");
        assertThat(user.getEmail()).isEqualTo("vestibulum@eu.org");
        // Home address
        assertThat(user.getAddress()).isNotNull();
        assertThat(user.getAddress().getStreet()).isEqualToIgnoringCase("Ap #210-8344 Eu Avenue");
        assertThat(user.getAddress().getZipCode()).isEqualToIgnoringCase("4711");
        assertThat(user.getAddress().getCity()).isEqualToIgnoringCase("Masone");
    }


    @Test
    public void should_save_new_user() {
        // given
        User3 newUser = new User3();
        newUser.setFirstName("Luke");
        newUser.setLastName("Skywalker");
        newUser.setEmail("luke@skywalker.net");
        Address address = new Address();
        address.setStreet("Uncle's Farm");
        address.setCity("Tatooin");
        address.setZipCode("FLKD 2045");
        newUser.setAddress(address);

        Integer rowBeforeSave = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_3", Integer.class);

        // when
        entityManager.persist(newUser);
        entityManager.flush();
        Integer rowAfterSave = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_3", Integer.class);
        // un autre query de jdbcTemplate pour aller plus loin dans la vérification
        // jdbcTemplate.query...

        // then
        assertThat(rowAfterSave).isEqualTo(rowBeforeSave + 1);
        // verifier que l'adresse est sauvegardé comme vous le souhaitez
    }
}
