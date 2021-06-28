import com.sgumiel.prices.PricesApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(classes = PricesApplication.class )
@ActiveProfiles("test")
class PricesApplicationTests extends Specification {

    @Autowired(required = false)
    private ApplicationContext applicationContext

    def "when context is loaded then all expected beans are created"() {
        expect: "the application context is created"
        applicationContext;
    }
}