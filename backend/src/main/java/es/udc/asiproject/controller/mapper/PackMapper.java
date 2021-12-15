package es.udc.asiproject.controller.mapper;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.AccommodationDto;
import es.udc.asiproject.controller.dto.ActivityDto;
import es.udc.asiproject.controller.dto.PackDto;
import es.udc.asiproject.controller.dto.TransportDto;
import es.udc.asiproject.controller.dto.TravelDto;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.persistence.model.Product;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;

@Component
public class PackMapper {
	private static ModelMapper mapper;

	@Autowired
	private PackMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
		mapper.addMappings(new PropertyMap<Pack, PackDto>() {
			@Override
			protected void configure() {
				Converter<Byte[], String> byteArrayToStringConverter = new Converter<Byte[], String>() {
					@Override
					public String convert(MappingContext<Byte[], String> context) {
						byte[] bytes = new byte[context.getSource().length];
						for (int i = 0; i < context.getSource().length; i++) {
							bytes[i] = context.getSource()[i];
						}

						return new String(bytes);
					}
				};

				using(byteArrayToStringConverter).map(source.getImage()).setImage(null);
			}
		});
		mapper.addMappings(new PropertyMap<PackDto, Pack>() {
			@Override
			protected void configure() {
				Converter<String, Byte[]> stringToByteArrayConverter = new Converter<String, Byte[]>() {
					@Override
					public Byte[] convert(MappingContext<String, Byte[]> context) {
						Byte[] bytes = new Byte[context.getSource().getBytes().length];
						int i = 0;
						for (byte b : context.getSource().getBytes()) {
							bytes[i++] = b;
						}

						return bytes;
					}
				};

				using(stringToByteArrayConverter).map(source.getImage()).setImage(null);
			}
		});
	}

	public static PackDto convertToDto(Pack pack) {
		Set<AccommodationDto> accommodations = new HashSet<AccommodationDto>();
		Set<ActivityDto> activities = new HashSet<ActivityDto>();
		Set<TransportDto> transports = new HashSet<TransportDto>();
		Set<TravelDto> travels = new HashSet<TravelDto>();
		for (Product product : pack.getProducts()) {
			if (product instanceof Accommodation) {
				accommodations.add(AccommodationMapper.convertToDto((Accommodation) product));
			} else if (product instanceof Activity) {
				activities.add(ActivityMapper.convertToDto((Activity) product));
			} else if (product instanceof Transport) {
				transports.add(TransportMapper.convertToDto((Transport) product));
			} else if (product instanceof Travel) {
				travels.add(TravelMapper.convertToDto((Travel) product));
			}
		}
		PackDto packDto = mapper.map(pack, PackDto.class);
		packDto.setAccommodations(accommodations);
		packDto.setActivities(activities);
		packDto.setTransports(transports);
		packDto.setTravels(travels);

		return packDto;
	}

	public static Pack convertToEntity(PackDto packDto) {
		Set<Product> products = new HashSet<Product>();
		for (AccommodationDto accommodationDto : packDto.getAccommodations()) {
			products.add(AccommodationMapper.convertToEntity(accommodationDto));
		}
		for (ActivityDto activityDto : packDto.getActivities()) {
			products.add(ActivityMapper.convertToEntity(activityDto));
		}
		for (TransportDto transportDto : packDto.getTransports()) {
			products.add(TransportMapper.convertToEntity(transportDto));
		}
		for (TravelDto travelDto : packDto.getTravels()) {
			products.add(TravelMapper.convertToEntity(travelDto));
		}
		Pack pack = mapper.map(packDto, Pack.class);
		pack.setProducts(products);

		return pack;
	}
}
