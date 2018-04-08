/*
package igorders;

import igorders.web.FileUploadController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:application.properties"})
public class TestPublishOrders {


    @Autowired
    FileUploadController fileUpload;

    @Value( "${spring.activemq.brokerurl}" )
    private String serverURL;

    @Value( "${spring.activemq.user}" )
    String userName;
    @Value( "${spring.activemq.password}" )
    String password;
    @Value( "${spring.activemq.destName}" )
    String destName;
    @Value( "${spring.activemq.queue}" )
    String queuetype;
    @Value( "${spring.activemq.topic}" )
    String topictype;

    @Test
    public void testQueueProducer() throws JMSException {
        try {
            fileUpload.upload(getMockMultiFile(), serverURL, userName, password, destName, queuetype);
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testTopicProducer() throws JMSException {
        try {
            fileUpload.upload(getMockMultiFile(), serverURL, userName, password, destName, topictype);
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }
    private MultipartFile getMockMultiFile() {
        Path path = Paths.get("/resources/SampleOrders.xml");
        String name = "SampleOrders.xml";
        String originalFileName = "SampleSingleOrders.xml";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        return (MultipartFile)new MockMultipartFile(name,
                originalFileName, contentType, content);
    }
}
*/
