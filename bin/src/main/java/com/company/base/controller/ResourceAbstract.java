package com.company.base.controller;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ResourceAbstract {

	default URI getUri(Object... uriData) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(uriData).toUri();
	}
	
	default ObjectNode errorFromEvelope(String message) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("errorMessage", message);
		
		return node;
	}
}
