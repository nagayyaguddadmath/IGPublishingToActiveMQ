package igorders.publish;

import igorders.XMLUtils.DecodeXML;
import igorders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Properties;

@Component
public class JMSOrderPublisher {

    private static final String CONTEXT_FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    private static final String TOPIC_CF = "TopicConnectionFactory";
    private static final String QUEUE_CF = "QueueConnectionFactory";
    private static final String TOPIC = "topic";
    private static final String QUEUE = "queue";


    @Autowired
    private DecodeXML decodeXML;

    public void publishMessage(String tcpServerURL, String username, String password, boolean isQueue,
                               String nameOfDestination, List<Order> orders) throws JAXBException, JMSException, NamingException {

        Connection connection = null;
        Session session;
        MessageProducer producer;
        Destination destination = null;

        try {
            Properties env = setMQServerProperties(tcpServerURL);

            InitialContext jndi = null;

            // Look up a JMS connection factory
            if (!isQueue) {
                env.setProperty(TOPIC + "." + nameOfDestination, nameOfDestination);
                jndi = new InitialContext(env);

                TopicConnectionFactory conFactory = null;
                conFactory = (TopicConnectionFactory) jndi.lookup(TOPIC_CF);

                // Create a JMS connection
                connection = conFactory.createTopicConnection(username, password);
            } else {
                env.setProperty(QUEUE + "." + nameOfDestination, nameOfDestination);
                jndi = new InitialContext(env);

                QueueConnectionFactory conFactory = null;
                conFactory = (QueueConnectionFactory) jndi.lookup(QUEUE_CF);

                // Create a JMS connection
                connection = conFactory.createQueueConnection(username, password);
            }

            destination = (Destination) jndi.lookup(nameOfDestination);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            for (Order order : orders) {
                TextMessage message = session.createTextMessage();
                message.setText(decodeXML.marshellOrder(order));
                producer.send(message);
            }

        } catch (NamingException | JAXBException | JMSException e) {
            System.out.println(" Error occurred while publishing orders: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ignored) {
                }
            }
        }
    }

    private Properties setMQServerProperties(String tcpServerURL) {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
        env.setProperty(Context.PROVIDER_URL, tcpServerURL);

        return env;
    }

    public void setDecodeXML(DecodeXML decodeXML) {
        this.decodeXML = decodeXML;
    }


}
