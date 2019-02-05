package com.company.base.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.company.base.model.Url;
import com.company.base.service.UrlRetrieverService;

public class UrlRetrieverControllerTest {

	private static UrlRetrieverController controller;
	private static UrlRetrieverService mockedService;
	private static BindingResult mockedBindingResult;
	private static Model mockedModel;
	
	@BeforeClass
	public static void init() {
		mockedModel = mock(Model.class);
		mockedBindingResult = mock(BindingResult.class);
		mockedService = mock(UrlRetrieverService.class);
		controller = new UrlRetrieverController(mockedService);
	}
	
	@Test
    public void whenCalledSaveGivenValidDataUrlThenCorrect() {		
        Url url = new Url("http://www.uol.com.br");
       
        ModelAndView mv = controller.saveUrl(url, mockedBindingResult, mockedModel);
        
        assertEquals(mv.getViewName(),"/index");
	}
	
	@Test
    public void whenCalledSaveUrlGivenInvalidDataThenError() {		
		Url url = new Url("http://www.malformedUrl.com.br");
		BindingResult mockedBindingResult = mock(BindingResult.class);
		when(mockedBindingResult.hasErrors()).thenReturn(true);

		ModelAndView mv = controller.saveUrl(url, mockedBindingResult, mockedModel);
		
		assertEquals(mv.getViewName(), "/url-retriever-error");
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void whenCalledShowUpdateFormAndInvalidDataThenError() {		
		
		long invalidId = 8;
		mockedService = mock(UrlRetrieverService.class);
		when(mockedService.findById(invalidId)).thenThrow(IllegalArgumentException.class);
		controller = new UrlRetrieverController(mockedService);
		
		controller.showUpdateForm(invalidId, mockedModel);
	}
	
	@Test
    public void whenCalledShowUpdateFormGivenValidDataThenCorrect() {		
		
		long validId = 9;
		mockedService = mock(UrlRetrieverService.class);
		Url mockedUrl = mock(Url.class);
		when(mockedService.findById(validId)).thenReturn(Optional.of(mockedUrl));
		controller = new UrlRetrieverController(mockedService);
		
		String showUpdate = controller.showUpdateForm(validId, mockedModel);
		
		assertEquals(showUpdate,"update-url");
	}
	
	
	@Test(expected=IllegalArgumentException.class)
    public void whenCalledDeleteGivenInvalidDataThenError() {		
		
		long invalidId = 8;
		mockedService = mock(UrlRetrieverService.class);
		when(mockedService.findById(invalidId)).thenThrow(IllegalArgumentException.class);
		controller = new UrlRetrieverController(mockedService);
		
		controller.deleteUrl(invalidId, mockedModel);
	}
	
	@Test
    public void whenCalledDeleteGivenValidDataThenCorrect() {		
		
		long invalidId = 9;
		mockedService = mock(UrlRetrieverService.class);
		Url mockedUrl = mock(Url.class);
		when(mockedService.findById(invalidId)).thenReturn(Optional.of(mockedUrl));
		controller = new UrlRetrieverController(mockedService);
		
		String showUpdate = controller.deleteUrl(invalidId, mockedModel);
		
		assertEquals(showUpdate, "index");
	}
	
}
