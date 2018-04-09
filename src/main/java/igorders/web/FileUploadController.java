package igorders.web;

import igorders.XMLUtils.DecodeXML;
import igorders.domain.OrderProcessor;
import igorders.publish.JMSOrderPublisher;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

    private static final String QUEUE = "Queue";
    @Autowired
    private OrderProcessor orderProcessor;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file, String serverURL, String userName, String password, String destName, String type) throws IOException, JMSException {

        InputStream is = null;
        try {
            if (!file.isEmpty()) {
                is = file.getInputStream();

                try {
                    orderProcessor.mapAndProcess(serverURL, userName, password, QUEUE.equals(type), destName, is);
                } catch (JAXBException | NamingException e) {
                    String errMsg = e.getMessage() == null ? e.toString() : e.getMessage();
                    System.out.println(" Error occurred while processing order request: " + errMsg);
                    return "redirect:/?success=false&error=" + errMsg;
                }
                return "redirect:/?success=true";
            } else {
                return "redirect:/?success=false&error=EmptyFile";
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}
