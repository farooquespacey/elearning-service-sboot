package com.spacey.springboot.course;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Custom serializer for Course class by ignoring its other properties in JSON.
 * 
 * @author Spacey4uq
 *
 */
class CourseMaterialDeserializer extends StdDeserializer<CourseMaterial> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseMaterialDeserializer() {
		this(null);
	}

	public CourseMaterialDeserializer(Class<CourseMaterial> t) {
		super(t);
	}

	@Override
	public CourseMaterial deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		String materialUrl = node.textValue();
		return new CourseMaterial(materialUrl);
	}

}