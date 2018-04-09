package igorders.domain;

import igorders.XMLUtils.DecodeXML;
import igorders.model.Order;
import igorders.publish.JMSOrderPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class OrderProcessor {

    @Autowired
    private DecodeXML decodeXML;

    @Autowired
    private JMSOrderPublisher dynPublisher;


    public void mapAndProcess(String tcpServerURL, String username, String password, boolean isQueue,
                              String nameOfDestination, InputStream is) throws JAXBException, JMSException, NamingException, IOException {

        List<Order> orders = decodeXML.unmarshellOrdersSequentially(is);

        dynPublisher.publishMessage(tcpServerURL, username, password, isQueue, nameOfDestination, orders);

    }
}
