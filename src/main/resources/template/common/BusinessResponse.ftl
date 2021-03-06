package ${_package}.common;

public class BusinessResponse {
private String code;
private Object data;
private String msg;

public BusinessResponse(String code, Object data, String msg) {
this.code = code;
this.data = data;
this.msg = msg;
}

public BusinessResponse(String code, String msg) {
this.code = code;
this.msg = msg;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public Object getData() {
return data;
}

public void setData(Object data) {
this.data = data;
}
}
