package igorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
//@ImportResource("classpath*:/springintegration-config.xml")
@EnableJms
public class IGPublishMain {

//	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

	public static void main(String[] args) {
		SpringApplication.run(IGPublishMain.class, args);
	}

/*	@Bean
	public TomcatEmbeddedServletContainerFactory containerFactory() {
		return new TomcatEmbeddedServletContainerFactory() {
			protected void customizeConnector(Connector connector) {
				super.customizeConnector(connector);
				if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
					(AbstractHttp11Protocol <?>) connector.getProtocolHandler()).setMaxSwallowSize(value);
				}
			}
		};

	}*/
	/*  @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });

        return tomcat;

    }
	 */
}
