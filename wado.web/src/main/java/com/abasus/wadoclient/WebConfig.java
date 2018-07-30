package com.abasus.wadoclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/*
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/").setViewName("forward:/wado/index.html");
	           
		    registry.addViewController("/bein/**").setViewName("forward:/wado/bein/index.html");
	      
	       
	    }
	 */
	 
	 @Bean
	 public InternalResourceViewResolver viewResolver() {
	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	  resolver.setPrefix("/");
	  resolver.setSuffix(".html");
	  return resolver;
	 }
	
 
	 
	 
	 /*
	@RequestMapping(value={"/", "/wado"}, method = RequestMethod.GET)
	public String login(){
		return "index.html";
	}
	
	
	@RequestMapping(value={ "/wado/bein"}, method = RequestMethod.GET)
	public String wado(){
		return "/wado/bein/index.html";
	}
	
	*/
	
	 
	 @Override
	    public void configureDefaultServletHandling(
	            DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
}
