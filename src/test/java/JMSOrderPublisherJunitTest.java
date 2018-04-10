import igorders.XMLUtils.DecodeXML;
import igorders.model.BuySale;
import igorders.model.Order;
import igorders.publish.JMSOrderPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JMSOrderPublisherJunitTest {

    @InjectMocks
    JMSOrderPublisher jmsOrderPublisher;

    private String serverURL = "tcp://localhost:61616";
    private String userName = "admin";
    private String password = "admin";
    private String destName = "dest-1";
    private String fileName = "SampleSIngleOrder.xml";

    @Test
    public void testJMSOrderPublisherQueue() {

        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(createOrder());
        orders.add(createOrder());

        jmsOrderPublisher.setDecodeXML(new DecodeXML());
        try {
            jmsOrderPublisher.publishMessage(serverURL, userName, password, true, destName, orders);
        } catch (JAXBException e) {
            e.printStackTrace();
            assert false;
        } catch (JMSException e) {
            assert e.getMessage().contains("Could not connect to broker URL: tcp://localhost:61616");
        } catch (NamingException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testJMSOrderPublisherTopic() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(createOrder());
        orders.add(createOrder());

        jmsOrderPublisher.setDecodeXML(new DecodeXML());
        try {
            jmsOrderPublisher.publishMessage(serverURL, userName, password, true, destName, orders);
        } catch (JAXBException e) {
            e.printStackTrace();
            assert false;
        } catch (JMSException e) {
            assert e.getMessage().contains("Could not connect to broker URL: tcp://localhost:61616");
        } catch (NamingException e) {
            e.printStackTrace();
            assert false;
        }
    }

    private Order createOrder () {
        Order order = new Order();
        order.setAccountName("TEST");
        order.setBuySale(BuySale.BUY);
        order.setMarketName("LONDON");
        order.setReceivedAt(1234567788);
        order.setSubmittedAt(765832765);
        order.setSize(100);

        return order;
    }

}
