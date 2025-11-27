package com.property.dto;

import java.util.List;

import com.property.entity.RoomsAvailability;

public class APIResponse<T> {
private String message;
private int status;
private T data;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public T getData() {
	return data;
}
public void setData(T data) {
	this.data = data;
}

	


}
