package main.java.model.base;

import java.io.Serializable;

import javax.inject.Named;

/**
 * This is a Base Model with id
 * @Author shreyas patil
 */
@Named
public abstract class BaseModel implements Serializable{
	
	/**
	 * @Author shreyas patil
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null != obj && obj instanceof BaseModel && 
				((BaseModel)obj).getId().equals(this.id))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		if(id == null)
			return 0 ;
		else
			return id.intValue();
	}

}
