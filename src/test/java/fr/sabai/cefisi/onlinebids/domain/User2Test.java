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
public class User2Test {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void should_read_user_with_id_1() {
        // given
        long id = 1L;

        // when
        User2 user = entityManager.find(User2.class, id);

        // then

        //User
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Sarah");
        assertThat(user.getLastName()).isEqualTo("Schultz");
        assertThat(user.getEmail()).isEqualTo("pharetra.sed@tortorNunc.edu");
        // Home address
        assertThat(user.getHomeAddress()).isNotNull();
        assertThat(user.getHomeAddress().getStreet()).isEqualToIgnoringCase("P.O. Box 338, 1892 Rhoncus. St.");
        assertThat(user.getHomeAddress().getZipCode()).isEqualToIgnoringCase("14916");
        assertThat(user.getHomeAddress().getCity()).isEqualToIgnoringCase("Owensboro");
        // Billing address
        assertThat(user.getBillingAddress()).isNotNull();
        assertThat(user.getBillingAddress().getStreet()).isEqualToIgnoringCase("370-5662 Ultrices. Street");
        assertThat(user.getBillingAddress().getZipCode()).isEqualToIgnoringCase("93783");
        assertThat(user.getBillingAddress().getCity()).isEqualToIgnoringCase("Lakewood");
    }


    @Test
    public void should_save_new_user() {
        // given
        User2 newUser = new User2();
        newUser.setFirstName("Luke");
        newUser.setLastName("Skywalker");
        newUser.setEmail("luke@skywalker.net");
        Address address = new Address();
        address.setStreet("Uncle's farm");
        address.setZipCode("FLKD 2045");
        address.setCity("Tatooin");
        newUser.setHomeAddress(address);
        newUser.setBillingAddress(address);

        Integer rowBeforeSave = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_2", Integer.class);

        // when
        entityManager.persist(newUser);
        Integer rowAfterSave = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_2", Integer.class);
        // un autre query de jdbcTemplate pour aller plus loin dans la vérification
        // jdbcTemplate.query...

        // then
        assertThat(rowAfterSave).isEqualTo(rowBeforeSave + 1);
    }
}
