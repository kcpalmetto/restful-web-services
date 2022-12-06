package com.kc.restwebservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping(path="/helloworld")
	public String helloworld() {
		return "HelloWorld Kcc";
	}
	@GetMapping(path="/helloworldbean")
	public HelloworldBean helloworldbean() {
		return new HelloworldBean("Helloword bean kcc");
	}
	@GetMapping(path="/helloworldbean/pathvariable/{name}")
	public HelloworldBean helloworldPathVariable(@PathVariable String name) {
		return new HelloworldBean(String.format("Hello world,,,%s", name));
	}
	
	@GetMapping(path="/helloworldInter")
	public String helloworldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		
		return messageSource.getMessage("good.morning.message", null, "default good morning", locale);
	}
}
