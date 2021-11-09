package es.udc.asiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.controller.dto.PackDto;
import es.udc.asiproject.controller.dto.PageDto;
import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;
import es.udc.asiproject.controller.mapper.PackMapper;
import es.udc.asiproject.controller.mapper.PageMapper;
import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.service.PackService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@RestController
@RequestMapping("/packs")
public class PackController {
    @Autowired
    private PackService packService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PackDto createPack(@Validated({ InsertValidation.class }) @RequestBody PackDto packDto)
	    throws InstanceNotFoundException, InvalidOperationException {
	Pack pack = PackMapper.convertToEntity(packDto);

	return PackMapper.convertToDto(packService.createPack(pack));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageDto<PackDto> findPacks(@RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "8") int pageSize) {
	return PageMapper.convertToDto(packService.findPacks(page, pageSize), PackMapper::convertToDto);
    }

    @GetMapping("/hidden")
    @ResponseStatus(HttpStatus.OK)
    public PageDto<PackDto> findAllPacks(@RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "8") int pageSize) {
	return PageMapper.convertToDto(packService.findAllPacks(page, pageSize), PackMapper::convertToDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PackDto updatePack(@Validated({ UpdateValidation.class }) @RequestBody PackDto packDto)
	    throws InstanceNotFoundException, InvalidOperationException {
	Pack pack = PackMapper.convertToEntity(packDto);

	return PackMapper.convertToDto(packService.updatePack(pack));
    }

    @PutMapping("/toogleHighlight/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toogleHighlightPack(Long userId, @PathVariable("id") Long packId)
	    throws InstanceNotFoundException, InvalidOperationException {

	packService.toogleHighlightPack(packId);
    }

    @PutMapping("/toogleHide/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toogleHidePack(Long userId, @PathVariable("id") Long packId)
	    throws InstanceNotFoundException, InvalidOperationException {

	packService.toogleHidePack(packId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAccommodation(@PathVariable("id") Long id) throws InstanceNotFoundException {
	packService.removePack(id);
    }
}
