package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.asiproject.controller.dto.SaleDto;
import es.udc.asiproject.persistence.model.Sale;

public class SaleMapper {
	private static ModelMapper mapper;

	@Autowired
	private SaleMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static SaleDto convertToDto(Sale sale) {
//		System.out.println(sale.getAgent().getId());
//		System.out.println(sale.getClient().getId());
//		System.out.println("====================================");
//		System.out.println(sale);
//		SaleDto saleDto = mapper.map(sale, SaleDto.class);
//		System.out.println(saleDto);

		SaleDto saleDto = new SaleDto();
		saleDto.setId(sale.getId());
		saleDto.setState(sale.getState());
		saleDto.setPrice(sale.getPrice());
		saleDto.setAgent(UserMapper.convertToDto(sale.getAgent()));
		saleDto.setClient(UserMapper.convertToDto(sale.getClient()));
		saleDto.setAccommodations(AccommodationMapper.convertToDto(sale.getAccommodations()));
		saleDto.setActivities(ActivityMapper.convertToDto(sale.getActivities()));
		saleDto.setTransports(TransportMapper.convertToDto(sale.getTransports()));
		saleDto.setTravels(TravelMapper.convertToDto(sale.getTravels()));
		return saleDto;
	}

	public static Sale convertToEntity(SaleDto saleDto) {

		Sale sale = new Sale();
		sale.setId(saleDto.getId());
		sale.setState(saleDto.getState());
		sale.setPrice(saleDto.getPrice());
		sale.setAgent(UserMapper.convertToEntity(saleDto.getAgent()));
		sale.setClient(UserMapper.convertToEntity(saleDto.getClient()));
		sale.setAccommodations(AccommodationMapper.convertToEntity(saleDto.getAccommodations()));
		sale.setActivities(ActivityMapper.convertToEntity(saleDto.getActivities()));
		sale.setTransports(TransportMapper.convertToEntity(saleDto.getTransports()));
		sale.setTravels(TravelMapper.convertToEntity(saleDto.getTravels()));

		return sale;
	}

}
