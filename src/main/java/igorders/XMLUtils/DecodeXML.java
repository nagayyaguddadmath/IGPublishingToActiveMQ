package igorders.XMLUtils;

import igorders.model.Order;
import igorders.model.Orders;
import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * Reads Given is and returns the Orders
 */
@Component
public class DecodeXML {

    public List<Order> unmarshellOrdersSequentially(InputStream inputStreamReader) throws JAXBException, IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStreamReader))) {

            List<Order> orders = new ArrayList<>();
            buffer.readLine();
            while(buffer.ready())
            {
                String orderXMLLine = buffer.readLine();
                orders.add(unmarshellOrdersByObject(orderXMLLine));
            }
            return orders;
        }
    }

	public Order unmarshellOrdersByObject(String orderXMLLine) throws JAXBException{
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller = null;

        try {
            jaxbContext = JAXBContext.newInstance(Order.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(orderXMLLine);
//            jaxbUnmarshaller.unmarshal(inputStreamReader);

            return (Order) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            System.out.println("Error while Unmarshelling Orders from given file: " + e.getMessage());
            throw e;
        }

    }

    public Orders unmarshellOrders(InputStream inputStreamReader) throws JAXBException{
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller = null;

        try {
            jaxbContext = JAXBContext.newInstance(Orders.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            jaxbUnmarshaller.unmarshal(inputStreamReader);
//            jaxbUnmarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            JAXBElement<Orders> ordersRoot = (JAXBElement<Orders>)jaxbUnmarshaller.unmarshal(new StreamSource(inputStreamReader), Orders.class);
            return ordersRoot.getValue();//ordersRoot.getOrders();
        } catch (JAXBException e) {
            System.out.println("Error while Unmarshelling Orders from given file" + e.getMessage());
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
