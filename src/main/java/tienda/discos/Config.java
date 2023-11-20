package tienda.discos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tienda.discos.interceptores.InterceptorAdmin;

@Component
public class Config implements WebMvcConfigurer{

	@Autowired
	private InterceptorAdmin interceptorAdmin;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAdmin);
	}
	
	
	
}
