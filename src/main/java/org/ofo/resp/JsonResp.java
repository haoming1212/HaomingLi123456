package org.ofo.resp;

public class JsonResp {

    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public static JsonResp ok(){
        JsonResp resp = new JsonResp();
        resp.setCode(200);
        return resp;
    }

    public static JsonResp ok(Object data){
        JsonResp resp = new JsonResp();
        resp.setCode(200);
        resp.setData(data);
        return resp;
    }

    public static JsonResp error(String msg) {
        JsonResp resp = new JsonResp();
        resp.setCode(500);
        resp.setMsg(msg);
        return resp;
    }
}
