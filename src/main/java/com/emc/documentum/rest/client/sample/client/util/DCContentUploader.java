/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StreamUtils;

public class DCContentUploader {
    private final String url;
    private int status;
    private HttpHeaders headers;
    private String response;
    private final InputStream content;
    
    public DCContentUploader(String url, InputStream content) {
        this.url = url;
        this.content = content;
    }
    
    public DCContentUploader upload() throws IOException
    {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/octet-stream");
        post.setEntity(new InputStreamEntity(content));

        HttpResponse httpResponse = client.execute(post);
        status = httpResponse.getStatusLine().getStatusCode();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StreamUtils.copy(httpResponse.getEntity().getContent(), os);
        response = os.toString("UTF-8");
        headers = new HttpHeaders();
        for(Header header : httpResponse.getAllHeaders()) {
            headers.add(header.getName(), header.getValue());
        }
        post.releaseConnection();
        return this;
    }

    public int getStatus() {
        return status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getResponse() {
        return response;
    }
}
