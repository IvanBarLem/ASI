package es.udc.asiproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.Transport;
import es.udc.asiproject.backend.model.entities.TransportDao;
import es.udc.asiproject.backend.model.entities.TransportType;
import es.udc.asiproject.backend.model.entities.TransportTypeDao;
import es.udc.asiproject.backend.model.services.BadDateFormatException;
import es.udc.asiproject.backend.model.services.Block;
import es.udc.asiproject.backend.model.services.DateBeforeTodayException;
import es.udc.asiproject.backend.model.services.TransportService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TransportServiceTest {

    @Autowired
    TransportService transportService;

    @Autowired
    TransportDao transportDao;

    @Autowired
    TransportTypeDao transportTypeDao;

    public TransportType createTransportType(String name) {
	TransportType type = new TransportType();
	type.setName(name);
	return transportTypeDao.save(type);
    }

    @Test
    public void testAddTransport() throws InstanceNotFoundException, DateBeforeTodayException, BadDateFormatException {
	TransportType type = createTransportType("Avion");
	Transport transport = transportService.addTransport(type.getId(), "2030-12-03T10:15:30+01:00", "A Coruña",
		"Roma");
	Optional<Transport> foundTransport = transportDao.findById(transport.getId());
	assertTrue(foundTransport.isPresent());
	assertEquals(transport, foundTransport.get());
    }

    @Test
    public void testFindTransports()
	    throws InstanceNotFoundException, DateBeforeTodayException, BadDateFormatException {
	TransportType type1 = createTransportType("Avion");
	TransportType type2 = createTransportType("Bus");
	TransportType type3 = createTransportType("Barco");
	Transport transport1 = transportService.addTransport(type1.getId(), "2030-12-03T10:15:30+01:00", "A Coruña",
		"Roma");
	Transport transport2 = transportService.addTransport(type1.getId(), "2040-12-03T10:15:30+01:00", "Santiago",
		"Florencia");
	Transport transport3 = transportService.addTransport(type2.getId(), "2050-12-03T10:15:30+01:00", "A Coruña",
		"Roma");
	Transport transport4 = transportService.addTransport(type3.getId(), "2040-12-03T10:15:30+01:00", "A Coruña",
		"Roma");

	Block<Transport> transportsType = transportService.findTransports(type3.getId(), "", "", "", "", 0, 10);
	assertEquals(transportsType.getItems(), Arrays.asList(transport4));

	Block<Transport> transportsDates = transportService.findTransports(null, "2029-12-03T10:15:30+01:00",
		"2041-12-03T10:15:30+01:00", "", "", 0, 10);
	assertEquals(transportsDates.getItems(), Arrays.asList(transport1, transport2, transport4));

	Block<Transport> transportsOrigin = transportService.findTransports(null, "", "", "coruña", "", 0, 10);
	assertEquals(transportsOrigin.getItems(), Arrays.asList(transport1, transport3, transport4));

	Block<Transport> transportsDestination = transportService.findTransports(null, "", "", "", "flOr", 0, 10);
	assertEquals(transportsDestination.getItems(), Arrays.asList(transport2));

    }

    @Test
    public void testFindAllTransporTypes() {
	TransportType type1 = createTransportType("Avion");
	TransportType type2 = createTransportType("Bus");
	TransportType type3 = createTransportType("Barco");
	TransportType type4 = createTransportType("Tren");

	List<TransportType> typeList = Arrays.asList(type1, type2, type3, type4);
	List<TransportType> foundList = transportService.getAllTransportTypes();

	assertEquals(typeList, foundList);
    }
}
