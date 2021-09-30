package es.udc.asiproject.backend.rest.controllers;

import static es.udc.asiproject.backend.rest.dtos.TransportConversor.toTransportDtos;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.model.entities.Transport;
import es.udc.asiproject.backend.model.services.BadDateFormatException;
import es.udc.asiproject.backend.model.services.Block;
import es.udc.asiproject.backend.model.services.DateBeforeTodayException;
import es.udc.asiproject.backend.model.services.TransportService;
import es.udc.asiproject.backend.rest.common.ErrorsDto;
import es.udc.asiproject.backend.rest.dtos.BlockDto;
import es.udc.asiproject.backend.rest.dtos.FindTransportsDto;
import es.udc.asiproject.backend.rest.dtos.TransportDto;

@RestController
@RequestMapping("/users")
public class TransportController {
    private static final String DATE_BEFORE_TODAY_EXCEPTION_CODE = "project.exceptions.DateBeforeTodayException";
    private static final String BAD_DATE_FORMAT_EXCEPTION_CODE = "project.exceptions.BadDateException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TransportService transportService;

    @ExceptionHandler(DateBeforeTodayException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleDateBeforeTodayException(DateBeforeTodayException exception, Locale locale) {
	String errorMessage = messageSource.getMessage(DATE_BEFORE_TODAY_EXCEPTION_CODE, null,
		DATE_BEFORE_TODAY_EXCEPTION_CODE, locale);
	return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(BadDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleBadDateFormatException(BadDateFormatException exception, Locale locale) {
	String errorMessage = messageSource.getMessage(BAD_DATE_FORMAT_EXCEPTION_CODE, null,
		BAD_DATE_FORMAT_EXCEPTION_CODE, locale);

	return new ErrorsDto(errorMessage);
    }

    @PostMapping("/addTransport")
    public void addTransport(@Validated @RequestBody TransportDto params)
	    throws DateBeforeTodayException, BadDateFormatException, InstanceNotFoundException {
	transportService.addTransport(params.getTransportTypeId(), params.getDate(), params.getOrigin(),
		params.getDestination());
    }

    @GetMapping("findTransports")
    public BlockDto<TransportDto> findTransports(@RequestAttribute Long userId, @RequestBody FindTransportsDto params,
	    @RequestParam(defaultValue = "0") int page) throws BadDateFormatException {
	Block<Transport> transports = transportService.findTransports(params.getTransportTypeId(),
		params.getStartDate(), params.getEndDate(), params.getOrigin(), params.getDestination(), page, 10);
	return new BlockDto<>(toTransportDtos(transports.getItems()), transports.getExistMoreItems());
    }
}
