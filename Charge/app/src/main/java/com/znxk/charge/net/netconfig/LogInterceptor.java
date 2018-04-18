package com.znxk.charge.net.netconfig;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * OKHTTP日志拦截器
 * Created by w on 2017/11/21.
 */

public class LogInterceptor implements Interceptor {
    public static final String TAG = "LogInterceptor.java";
    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1 = System.nanoTime();
        Request request = chain.request();
        //the request url
        String url = request.url().toString();
        //the request method
        String method = request.method();
//        Logger.w("type:"+method+"  url:"+url);
        //the request body
//        RequestBody requestBody = request.body();
//
//        Logger.e((requestBody!= null)+"");
//        if(requestBody!= null) {
//            StringBuilder sb = new StringBuilder("Request Body [");
//            okio.Buffer buffer = new okio.Buffer();
//            requestBody.writeTo(buffer);
//            Charset charset = Charset.forName("UTF-8");
//            MediaType contentType = requestBody.contentType();
//            Logger.e(requestBody.contentLength()+"");
//
//            if (contentType != null) {
//                charset = contentType.charset(charset);
//            }
//            if(isPlaintext(buffer)){
//                sb.append(buffer.readString(charset));
//                sb.append(" (Content-Type = ").append(contentType.toString()).append(",")
//                        .append(requestBody.contentLength()).append("-byte body)");
//            }else {
//                sb.append(" (Content-Type = ").append(contentType.toString())
//                        .append(",binary ").append(requestBody.contentLength()).append("-byte body omitted)");
//            }
//            sb.append("]");
//            Logger.w(method+sb.toString());
//        }

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        //the response time
        Logger.w("type:"+method+"  url:"+url+"  longtime:"+(t2-t1)/1e9d+"ms");
        //the response state
        Logger.w(response.message()+":"+response.code());
        Headers headers = response.headers();
        Logger.w(headers.toString());

        //the response data
        ResponseBody body = response.body();
        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
        MediaType contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        String bodyString = buffer.clone().readString(charset);
        Logger.json(bodyString);
        return response;
    }

//    static boolean isPlaintext(Buffer buffer){
//        try {
//            Buffer prefix = new Buffer();
//            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
//            buffer.copyTo(prefix, 0, byteCount);
//            for (int i = 0; i < 16; i++) {
//                if (prefix.exhausted()) {
//                    break;
//                }
//                int codePoint = prefix.readUtf8CodePoint();
//                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
//                    return false;
//                }
//            }
//            return true;
//        } catch (EOFException e) {
//            return false; // Truncated UTF-8 sequence.
//        }
//    }

}
