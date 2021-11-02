package es.udc.asiproject.controller.security.token;

public interface JwtGenerator {
	String generate(JwtInfo info);

	JwtInfo getInfo(String token);
}
