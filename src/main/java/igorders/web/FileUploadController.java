package igorders.web;

import java.io.IOException;
import java.io.InputStream;

import igorders.XMLUtils.DecodeXML;
import igorders.publish.JMSDynamicPublisher;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

@Controller
public class FileUploadController {

    @Autowired
    JMSDynamicPublisher dynPublisher;

    @Autowired
    DecodeXML decodeXML;

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String upload(@RequestParam MultipartFile file, String serverURL, String userName, String password, String destName, String type) throws IOException, JMSException {

        InputStream is = null;
		try {
            if (!file.isEmpty()) {


//			String fileName = file.getOriginalFilename();
			is = file.getInputStream();

                try {
                    dynPublisher.publishMessage(serverURL, userName, password, type.equals("Queue"), destName, decodeXML.unmarshellOrdersSequentially(is));
                } catch (JAXBException e) {
                    return "redirect:/?success=false&error=" + e.getMessage();
                }

//			dynPublisher.publishMessage("tcp://localhost:61616", "admin","admin", false,"interview-2", "DynamiC Message Nagayya");
			/*            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
			 */
                return "redirect:/?success=true";

            } else {

                return "redirect:/?success=false";
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
	}
}
