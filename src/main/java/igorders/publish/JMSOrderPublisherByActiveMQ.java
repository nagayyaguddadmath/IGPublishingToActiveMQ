package igorders.publish;

import igorders.model.Order;
import igorders.xmlutils.DecodeXML;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class JMSOrderPublisherByActiveMQ {

    @Autowired
    private DecodeXML decodeXML;

    public void publishMessage(String tcpServerURL, String username, String password, boolean isQueue,
                               String nameOfDestination, List<Order> orders) throws JAXBException, JMSException, NamingException {

        MessageProducer producer;

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(username, password, tcpServerURL);
//        factory.setTrustedPackages(Arrays.asList("com.h4p.server"));
        Session session = null;
        Destination destination = null;
        try (Connection connection = factory.createConnection()) {
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(nameOfDestination);

            producer = session.createProducer(destination);

            for (Order order : orders) {
                TextMessage message = session.createTextMessage();
                message.setText(decodeXML.marshellOrder(order));
                producer.send(message);
            }


        } catch (JMSException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void setDecodeXML(DecodeXML decodeXML) {
        this.decodeXML = decodeXML;
    }


}
