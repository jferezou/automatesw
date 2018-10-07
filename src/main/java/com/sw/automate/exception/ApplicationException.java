package com.sw.automate.exception;

public interface ApplicationException {

	public void setId(String id);

	public String getId();

	public boolean hasId();

	public boolean hasNoId();

	public String getMessage();

}
