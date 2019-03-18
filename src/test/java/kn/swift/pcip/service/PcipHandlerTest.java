package kn.swift.pcip.service;

import kn.swift.ms.pcip.dto.ObjectFactory;
import kn.swift.pcip.Application;
import kn.swift.pcip.configuration.properties.WmsProperties;
import kn.swift.pcip.service.printparcel.PrintParcelService;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreators;
import org.springframework.xml.transform.StringSource;
import swift.wms.w001.client.api.DefaultApi;
import swift.wms.w001.client.model.StoreExternalRefsForPackage;
import swift.wms.w001.client.model.StoreExternalRefsForPackageResponse;
import swift.wms.w001.client.model.UpdatePackageResponse;
import swift.wms.w001.client.model.UpdateTrackNumberForPackage;

import javax.xml.transform.Source;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class PcipHandlerTest {

    @Rule
    public EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

    @Autowired
    WmsProperties wmsProperties;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private PrintParcelService printParcelService;

    private MockWebServiceServer mockServer;

    @Mock
    private DefaultApi defaultApi;

    @Mock
    private UpdatePackageResponse updatePackageResponse;

    @Mock
    private StoreExternalRefsForPackage storeExternalRefsForPackage;

    @Mock
    private StoreExternalRefsForPackageResponse storeExternalRefsForPackageResponse;

    private ObjectFactory of = new ObjectFactory();

    private Map<String, Object> headers = new HashMap<>();

//    private final Object lock = new Object();

    @Before
    public void createServer() {
        mockServer = MockWebServiceServer.createServer(printParcelService.getPcipPrintParcelServiceClient().getPcipWebServiceTemplate());
        printParcelService.getUpdateTrackNumberForPackageClient().setWmsApi(defaultApi);
        printParcelService.getStoreExternalRefsForPackageClient().setWmsApi(defaultApi);
        headers.put("X_KN_SWIFT_WMS_UUID.", "UUID");
    }


    @Test
    public void handle() throws IOException {
        UpdateTrackNumberForPackage updateTrackNumberForPackage = new UpdateTrackNumberForPackage();
        updateTrackNumberForPackage.setPkgId("PKG00000000000052");
        updateTrackNumberForPackage.setTraknm("Sumfin else");

        when(defaultApi.updateTrackNumberForPackage(updateTrackNumberForPackage))
                .thenReturn(updatePackageResponse);
        when(defaultApi.storeExternalRefsForPackage(storeExternalRefsForPackage))
                .thenReturn(storeExternalRefsForPackageResponse);


        String packageConfirmation = new String(Files.readAllBytes(Paths.get("src/test/resources/UC_PC.xml")), "UTF-8");

        Source response = new StringSource(new String(Files.readAllBytes(Paths.get("src/test/resources/PrintParcelResponse.xml")), "UTF-8"));


        mockServer.expect(RequestMatchers.anything()).andRespond(ResponseCreators.withPayload(response));

        jmsTemplate.convertAndSend(wmsProperties.getJms().getDestination(), packageConfirmation);

        verify(defaultApi, timeout(10000).times(1)).updateTrackNumberForPackage(MockitoHamcrest.argThat(trackNumCallMatch(updateTrackNumberForPackage)));
    }

    Matcher<UpdateTrackNumberForPackage> trackNumCallMatch(UpdateTrackNumberForPackage updateTrackNumberForPackagem) {
        return new TypeSafeMatcher<UpdateTrackNumberForPackage>() {
            @Override
            protected boolean matchesSafely(UpdateTrackNumberForPackage item) {
                return item.getPkgId().equals("PKG00000000000052") && item.getTraknm().equals("Sumfin else");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Package and Track num match");
            }
        };
    }
}