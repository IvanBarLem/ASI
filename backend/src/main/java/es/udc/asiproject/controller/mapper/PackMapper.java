package es.udc.asiproject.controller.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.PackDto;
import es.udc.asiproject.persistence.model.Pack;

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
		return mapper.map(pack, PackDto.class);
	}

	public static Pack convertToEntity(PackDto packDto) {
		return mapper.map(packDto, Pack.class);
	}
}
