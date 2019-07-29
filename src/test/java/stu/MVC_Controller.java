package stu;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.PushBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MVC_Controller {

	@RequestMapping
	public void controllerMethod(
			WebRequest webRequest,
			NativeWebRequest nativeWebRequest,
			//
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			//
			PushBuilder pushBuilder,
			Principal principal,
			HttpMethod method,
			//java8 time API
			Locale locale,
			TimeZone timeZone,
			ZoneId zoneId,
			//
			InputStream inputStream,
			Reader reader,
			OutputStream outputStream,
			Writer writer,
			//
			HttpEntity<?> httpEntity,
			Error error,
			BindingResult result,
			SessionStatus sessionStatus,
			//
			@PathVariable String path,
			@MatrixVariable String matri,
			@RequestParam String username,
			@RequestHeader String header,
			@CookieValue String cookie,
			@RequestBody String body,
			@RequestPart String part,
			//
			ModelMap map
			) {
	}
	
}
