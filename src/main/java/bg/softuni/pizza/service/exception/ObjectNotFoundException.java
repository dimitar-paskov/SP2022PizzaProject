/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private final Long objectId;

	public ObjectNotFoundException(Long objectId) {
		this.objectId = objectId;
	}
	
	public Long getObjectId() {
		return objectId;
	}

}
