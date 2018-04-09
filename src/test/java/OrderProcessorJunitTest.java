import igorders.AppConfig;
import igorders.XMLUtils.DecodeXML;
import igorders.domain.OrderProcessor;
import igorders.publish.JMSOrderPublisher;
import igorders.web.FileUploadController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource("classpath:application-test.properties")
public class OrderProcessorJunitTest {


    @InjectMocks
    OrderProcessor orderProcessor;

    @Mock
    private JMSOrderPublisher dynPublisher;

    private String serverURL;
    private String userName;
    private String password;
    private String destName;
    private String queuetype;
    private String topictype;
    private String fileName = "SampleSIngleOrder.xml";

    @Test
    public void testOrderProcessor () {
        try {
            orderProcessor.setDecodeXML(new DecodeXML());
            orderProcessor.mapAndProcess(serverURL, userName, password, true, destName, this.getClass().getResourceAsStream(fileName));

        } catch (JAXBException e) {
            e.printStackTrace();
            assert false;
        } catch (JMSException e) {
            e.printStackTrace();
            assert false;
        } catch (NamingException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

}
