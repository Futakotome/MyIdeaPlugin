package ${_package + '.common'};

public class Constants {

public enum ResponseCode {

SUCCESS("0000", "成功"),
ERROR("9999", "失败");

private final String code;
private final String info;

ResponseCode(String code, String info) {
this.code = code;
this.info = info;
}

public String getCode() {
return code;
}

public String getInfo() {
return info;
}

}
}