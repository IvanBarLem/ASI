package es.udc.asiproject.controller.mapper;

import java.util.HashSet;
import java.util.Set;

import es.udc.asiproject.controller.dto.ProductDto;
import es.udc.asiproject.controller.dto.SaleDto;
import es.udc.asiproject.persistence.model.Product;
import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.persistence.model.SaleProduct;

public class SaleMapper {

	public static SaleDto convertToDto(Sale sale) {
		SaleDto saleDto = new SaleDto();
		saleDto.setId(sale.getId());
		saleDto.setState(sale.getState());
		saleDto.setPrice(sale.getPrice());
		saleDto.setAgent(UserMapper.convertToDto(sale.getAgent()));
		saleDto.setClient(UserMapper.convertToDto(sale.getClient()));
		Set<ProductDto> products = new HashSet<>();
		for (SaleProduct saleProduct : sale.getProducts()) {
			ProductDto productDto = new ProductDto();
			productDto.setId(saleProduct.getProduct().getId());
			productDto.setName(saleProduct.getProduct().getName());
			productDto.setNumber(saleProduct.getQuantity());

			Product concreteProduct = saleProduct.getProduct();

			if (concreteProduct.getType() == "Accommodation") {
				productDto.setType("ACCOMMODATION");
			} else if (concreteProduct.getType() == "Activity") {
				productDto.setType("ACTIVITY");
			} else if (concreteProduct.getType() == "Transport") {
				productDto.setType("TRANSPORT");
			} else if (concreteProduct.getType() == "Travel") {
				productDto.setType("TRAVEL");
			}

			products.add(productDto);
		}

		saleDto.setProducts(products);

		return saleDto;
	}

//	public static Sale createSaleConvertToEntity(CreateSaleParamsDto createSaleParamsDto) {
//
//		Sale sale = new Sale();
//		sale.setPrice(createSaleParamsDto.getPrice());
//		sale.setClient(createSaleParamsDto.getClientId());
//		sale.setAccommodations(AccommodationMapper.convertToEntity(createSaleParamsDto.getAccommodations()));
//		sale.setActivities(ActivityMapper.convertToEntity(createSaleParamsDto.getActivities()));
//		sale.setTransports(TransportMapper.convertToEntity(createSaleParamsDto.getTransports()));
//		sale.setTravels(TravelMapper.convertToEntity(createSaleParamsDto.getTravels()));
//		return sale;	
//	}
}
