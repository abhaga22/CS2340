package com.example.jongbeom.cs2340_m4;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jongbeom on 2017-02-11.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGGISTER_REQUEST_URL = ";" ;
    private Map<String, String> params;

    public RegisterRequest(String name, String username, int age, String password,
                           Response.Listener<String> listener) {
        super(Method.POST, REGGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age + "");
    }
        @Override
        public Map<String, String> getParams() {
            return params;
    }
}
