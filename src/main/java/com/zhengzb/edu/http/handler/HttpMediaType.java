package com.zhengzb.edu.http.handler;



public enum HttpMediaType {

    /** ALL */
    ALL_VALUE("*/*"),

    /** application/atom+xml */
    APPLICATION_ATOM_XML_VALUE("application/atom+xml"),

    /** application/cbor */
    APPLICATION_CBOR_VALUE("application/cbor"),

    /** application/x-www-form-urlencoded */
    APPLICATION_FORM_URLENCODED_VALUE("application/x-www-form-urlencoded"),

    /** application/json */
    APPLICATION_JSON_VALUE("application/json"),

    /** application/json;charset=UTF-8 */
    APPLICATION_JSON_UTF8_VALUE("application/json;charset=UTF-8"),

    /** application/octet-stream */
    APPLICATION_OCTET_STREAM_VALUE("application/octet-stream"),

    /** application/pdf */
    APPLICATION_PDF_VALUE("application/pdf"),

    /** application/problem+json */
    APPLICATION_PROBLEM_JSON_VALUE("application/problem+json"),

    /** application/problem+json;charset=UTF-8 */
    APPLICATION_PROBLEM_JSON_UTF8_VALUE("application/problem+json;charset=UTF-8"),

    /** application/problem+xml */
    APPLICATION_PROBLEM_XML_VALUE("application/problem+xml"),

    /** application/rss+xml */
    APPLICATION_RSS_XML_VALUE("application/rss+xml"),

    /** application/stream+json */
    APPLICATION_STREAM_JSON_VALUE("application/stream+json"),

    /** application/xhtml+xml */
    APPLICATION_XHTML_XML_VALUE("application/xhtml+xml"),

    /** application/xml */
    APPLICATION_XML_VALUE("application/xml"),

    /** image/gif */
    IMAGE_GIF_VALUE("image/gif"),

    /** image/jpeg */
    IMAGE_JPEG_VALUE("image/jpeg"),

    /** image/png */
    IMAGE_PNG_VALUE("image/png"),

    /** multipart/form-data */
    MULTIPART_FORM_DATA_VALUE("multipart/form-data"),

    /** multipart/mixed */
    MULTIPART_MIXED_VALUE("multipart/mixed"),

    /** multipart/related */
    MULTIPART_RELATED_VALUE("multipart/related"),

    /** text/event-stream */
    TEXT_EVENT_STREAM_VALUE("text/event-stream"),

    /** text/html */
    TEXT_HTML_VALUE("text/html"),

    /** text/markdown */
    TEXT_MARKDOWN_VALUE("text/markdown"),

    /** text/plain */
    TEXT_PLAIN_VALUE("text/plain"),

    /** text/xml */
    TEXT_XML_VALUE("text/xml");

    private final String value;

    HttpMediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
