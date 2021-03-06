import orders.domain.OrderProcessor;
import orders.webcontroller.FileUploadController;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import java.io.IOException;
import java.io.InputStream;

@RunWith(MockitoJUnitRunner.class)
public class FileUploadJunitTest {

    @InjectMocks
    FileUploadController fileUpload;

    @Mock
    OrderProcessor orderProcessor;

    private String serverURL;
    private String userName;
    private String password;
    private String destName;
    private String queuetype;
    private String topictype;

    private String fileName = "SampleSIngleOrder.xml";

    @Test
    public void testFileUpload() {
        try {
            fileUpload.upload( convertToMultiPartFIle(fileName),serverURL,
                    userName, password, destName, queuetype);

        } catch(JMSException e) {
         e.printStackTrace();
         assert false;
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }

    public MultipartFile convertToMultiPartFIle (String fileName) {
        InputStream is = this.getClass().getResourceAsStream(fileName);

        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = IOUtils.toByteArray(is);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(fileName,
                fileName, contentType, content);
    return result;
    }
}
