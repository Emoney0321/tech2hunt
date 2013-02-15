package com.tech.hunt;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriTemplate;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.tech.hunt.svo.Message;
import com.tech.hunt.svo.Messages;

@Controller
public class MessageController extends AbstractUtils {

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public String getMessages(ModelMap map, HttpServletRequest request) throws URISyntaxException {
		Query messageQuery = new Query("Message");
		List<Entity> entities = datastore.prepare(messageQuery).asList(FetchOptions.Builder.withLimit(500));
		Messages messages = new Messages();
		URI messagesUri = new URI(request.getRequestURI());
		messages.setUri(messagesUri);
		for (Entity entity : entities) {
			messages.addMessage(createMessage(entity, request));
		}
		map.addAttribute(messages);
		return "messages";
	}

	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public String create(Message message, ModelMap map, HttpServletRequest request) throws URISyntaxException, EntityNotFoundException {
		createOrUpdate(message, null);
		return getMessages(map, request);
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable String id, Message message, ModelMap map, HttpServletRequest request)
			throws URISyntaxException, EntityNotFoundException {
		createOrUpdate(message, id);
		return "redirect:/" + getMessages(map, request);
	}
	
	@RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id, ModelMap map, HttpServletRequest request) throws URISyntaxException {
		datastore.delete(KeyFactory.stringToKey(id));
		return "redirect:/" + getMessages(map, request);
	}
	
	@RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
	public String retrieveById(@PathVariable String id, ModelMap map, HttpServletRequest request)
			throws URISyntaxException, EntityNotFoundException {
		Entity entity = datastore.get(KeyFactory.stringToKey(id));
		map.addAttribute(createMessage(entity, request));
		return "message";
	}

	private Message createMessage(Entity entity, HttpServletRequest request) {
		Message message = new Message();
		message.setMessage((String) entity.getProperty("message"));
		message.setSubject((String) entity.getProperty("subject"));
		message.setUri(createMessageUri(entity, request));
		return message;
	}
	
	private void createOrUpdate(Message message, String id) throws EntityNotFoundException {
		Entity messageEntity;
		if (id == null) {
			messageEntity = new Entity("Message");
		} else {
			messageEntity = datastore.get(KeyFactory.stringToKey(id));
		}
		messageEntity.setProperty("subject", message.getSubject());
		messageEntity.setProperty("message", message.getMessage());
		datastore.put(messageEntity);
	}
	
	private URI createMessageUri(Entity entity, HttpServletRequest request) {
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("baseUrl", retrieveBaseRequestUrl(request));
		uriVariables.put("messageId", KeyFactory.keyToString(entity.getKey()));
		return new UriTemplate("{baseUrl}/messages/{messageId}").expand(uriVariables);
	}
	
	private String retrieveBaseRequestUrl(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String requestUrl = request.getRequestURL().toString();
		try {
			requestUrl = URLDecoder.decode(requestUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Failed to decode requestUrl", e);
		}

		int index = requestUrl.indexOf(requestUri);
		requestUrl = requestUrl.substring(0, index);

		return requestUrl;
	}
}
