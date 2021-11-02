package es.udc.asiproject.controller.mapper;

import es.udc.asiproject.controller.dto.PackDto;
import es.udc.asiproject.persistence.model.Pack;

public class PackMapper {
	public static PackDto convertToDto(Pack pack) {
		PackDto packDto = new PackDto();
		packDto.setId(pack.getId());
		packDto.setTitle(pack.getTitle());
		packDto.setDescription(pack.getDescription());
		packDto.setImage(new String(toPrimitive(pack.getImage())));
		packDto.setPrice(pack.getPrice());
		packDto.setDuration(pack.getDuration());
		packDto.setPersons(pack.getPersons());
		packDto.setAccommodations(AccommodationMapper.convertToDto(pack.getAccommodations()));
		packDto.setActivities(ActivityMapper.convertToDto(pack.getActivities()));
		packDto.setTransports(TransportMapper.convertToDto(pack.getTransports()));
		packDto.setTravels(TravelMapper.convertToDto(pack.getTravels()));

		return packDto;
	}

	public static Pack convertToEntity(PackDto packDto) {
		Pack pack = new Pack();
		pack.setId(packDto.getId());
		pack.setTitle(packDto.getTitle());
		pack.setDescription(packDto.getDescription());
		pack.setImage(toObject(packDto.getImage().getBytes()));
		pack.setPrice(packDto.getPrice());
		pack.setDuration(packDto.getDuration());
		pack.setPersons(packDto.getPersons());
		pack.setAccommodations(AccommodationMapper.convertToEntity(packDto.getAccommodations()));
		pack.setActivities(ActivityMapper.convertToEntity(packDto.getActivities()));
		pack.setTransports(TransportMapper.convertToEntity(packDto.getTransports()));
		pack.setTravels(TravelMapper.convertToEntity(packDto.getTravels()));

		return pack;
	}

	private static Byte[] toObject(byte[] bytesPrim) {
		Byte[] bytes = new Byte[bytesPrim.length];
		int i = 0;
		for (byte b : bytesPrim) {
			bytes[i++] = b;
		}

		return bytes;
	}

	private static byte[] toPrimitive(Byte[] oBytes) {

		byte[] bytes = new byte[oBytes.length];
		for (int i = 0; i < oBytes.length; i++) {
			bytes[i] = oBytes[i];
		}

		return bytes;
	}
}
