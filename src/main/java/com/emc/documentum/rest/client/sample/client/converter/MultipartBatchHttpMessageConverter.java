/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */

package com.emc.documentum.rest.client.sample.client.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

import com.emc.documentum.rest.client.sample.client.util.SupportedMediaTypes;

public class MultipartBatchHttpMessageConverter implements HttpMessageConverter<MultiValueMap<String, HttpEntity<InputStream>>> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return "multipart".equalsIgnoreCase(mediaType.getType()) &&
               "related".equalsIgnoreCase(mediaType.getSubtype()) &&
               MultiValueMap.class.isAssignableFrom(clazz);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(SupportedMediaTypes.MULTIPART_RELATED);
    }

    @Override
    public MultiValueMap<String, HttpEntity<InputStream>> read(Class<? extends MultiValueMap<String, HttpEntity<InputStream>>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(MultiValueMap<String, HttpEntity<InputStream>> parts, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        String boundary = MimeTypeUtils.generateMultipartBoundaryString();
        Map<String, String> params = new HashMap<>(contentType.getParameters());
        params.put("boundary", boundary);
        MediaType mediaType = new MediaType(contentType, params);
        outputMessage.getHeaders().setContentType(mediaType);
        writeParts(outputMessage.getBody(), parts, boundary);
    }
    
    private void writeParts(OutputStream os, MultiValueMap<String, HttpEntity<InputStream>> parts, String boundary) throws IOException {
        for (Map.Entry<String, List<HttpEntity<InputStream>>> entry : parts.entrySet()) {
            String name = entry.getKey();
            for (HttpEntity<InputStream> part : entry.getValue()) {
                if (part != null) {
                    writeBoundary(os, boundary);
                    writePart(name, part, os);
                    writeNewLine(os);
                }
            }
        }
        writeEnd(os, boundary);
    }

    private void writePart(String name, HttpEntity<InputStream> part, OutputStream os) throws IOException {
        writeHeaders(os, part.getHeaders());
        StreamUtils.copy(part.getBody(), os);
        writeNewLine(os);
    }
    
    private void writeHeaders(OutputStream os, HttpHeaders headers) throws IOException {
        for(Map.Entry<String, List<String>> entry : headers.entrySet()) {
            for(String value : entry.getValue()) {
                os.write(entry.getKey().getBytes("UTF-8"));
                os.write(':');
                os.write(' ');
                os.write(value.getBytes("UTF-8"));
                writeNewLine(os);
            }
        }
        writeNewLine(os);
    }

    private void writeBoundary(OutputStream os, String boundary) throws IOException {
        os.write(("--" + boundary).getBytes("UTF-8"));
        writeNewLine(os);
    }

    private void writeEnd(OutputStream os, String boundary) throws IOException {
        os.write(("--" + boundary + "--").getBytes("UTF-8"));
        writeNewLine(os);
    }

    private static void writeNewLine(OutputStream os) throws IOException {
        os.write('\r');
        os.write('\n');
    }
}
