package igorders.xmlutils;

import igorders.model.Order;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Reads Given is and returns the Orders
 */
@Component
public class DecodeXML {

    public List<Order> unmarshellOrdersSequentially(InputStream inputStreamReader) throws JAXBException, IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStreamReader))) {

            List<Order> orders = new ArrayList<>();
            buffer.readLine();
            while (buffer.ready()) {
                String orderXMLLine = buffer.readLine();
                orders.add(unmarshellOrdersByObject(orderXMLLine));
            }
            return orders;
        }
    }

    public Order unmarshellOrdersByObject(String orderXMLLine) throws JAXBException {
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller = null;

        try {
            jaxbContext = JAXBContext.newInstance(Order.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(orderXMLLine);

            return (Order) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            System.out.println("Error while Unmarshelling Orders from given file: " +
                    (e.getMessage() == null ? e.toString() : e.getMessage()));
            System.out.println("Error - Looks like given input file format is wrong ");
            throw e;
        }

    }

    public String marshellOrder(Order order) throws JAXBException {
        JAXBContext jaxbContext;
        Marshaller jaxbUnmarshaller = null;

        try {
            jaxbContext = JAXBContext.newInstance(Order.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(order, sw);

            return sw.toString();


        } catch (JAXBException e) {
            System.out.println("Error while Unmarshelling Orders from given file" + e.getMessage());
            throw e;
        }

    }
}
