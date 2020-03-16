package helperj.ctrl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class Ctrl {
    public static Map<String, Object> Result(Object content, double success) {
        Map<String, Object> res = new HashMap<>();
        res.put("content", content);
        res.put("success", success);
        return res;
    }

    public static void Res(Context ctx, Object content, double success, int status) {
        Map<String, Object> res = Result(content, success);
        ctx.status(status);
        ctx.contentType("application/json");
        GsonBuilder gbuilder = new GsonBuilder();
        Gson gson = gbuilder.serializeNulls().create();
        ctx.result(gson.toJson(res));
    }

    public static void Res(Context ctx, Object content, double success) {
        Res(ctx, content, success, 200);
    }

    public static void Succ(Context ctx, Object content) {
        Res(ctx, content, 1, 200);
    }

}
