package es.udc.asiproject;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import es.udc.asiproject.controller.dto.PackDto;
import es.udc.asiproject.persistence.model.Pack;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.addMappings(new PropertyMap<Pack, PackDto>() {
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
		modelMapper.addMappings(new PropertyMap<PackDto, Pack>() {
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

		return modelMapper;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();

		bean.setBasename("classpath:messages");
		bean.setDefaultEncoding("UTF-8");

		return bean;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();

		bean.setValidationMessageSource(messageSource());

		return bean;
	}
}
