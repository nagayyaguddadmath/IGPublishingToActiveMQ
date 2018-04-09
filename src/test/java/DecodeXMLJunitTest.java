import igorders.AppConfig;
import igorders.XMLUtils.DecodeXML;
import igorders.model.BuySale;
import igorders.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource("classpath:application-test.properties")
public class DecodeXMLJunitTest {

    @InjectMocks
    DecodeXML decodeXML;

    private String fileName2 = "SampleOrders.xml";
    private String fileName1 = "SampleSIngleOrder.xml";

    @Test
    public void testDecodeXMLWithTwoRec () {
        try {
            List<Order> orders = decodeXML.unmarshellOrdersSequentially(this.getClass().getResourceAsStream(fileName2));
            assert orders.size()==2;
        } catch (JAXBException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testDecodeXMLWithOneRec () {
        try {
            List<Order> orders = decodeXML.unmarshellOrdersSequentially(this.getClass().getResourceAsStream(fileName1));
            assert orders.size()==1;
        } catch (JAXBException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testDecodeXMLMarshell () {
        try {
            String strOrder = decodeXML.marshellOrder(createOrder());
            assert strOrder.length()>10;
            assert strOrder.length()>10;
            assert strOrder.contains("<Order>\n" +
                    "    <accont>TEST</accont>\n" +
                    "    <action>BUY</action>\n" +
                    "    <market>LONDON</market>\n" +
                    "    <ReceivedAt>1234567788</ReceivedAt>\n" +
                    "    <size>100</size>\n" +
                    "    <SubmittedAt>765832765</SubmittedAt>\n" +
                    "</Order>");
        } catch (JAXBException e) {
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
