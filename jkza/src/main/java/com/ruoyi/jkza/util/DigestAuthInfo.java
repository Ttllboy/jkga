package com.ruoyi.jkza.util;

public class DigestAuthInfo {
    private String username;
    private String realm;
    private String nonce;
    private String uri;
    private String response;
    private String qop;
    private String nc;
    public String cnonce;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRealm() {
        return realm;
    }
    public void setRealm(String realm) {
        this.realm = realm;
    }
    public String getNonce() {
        return nonce;
    }
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public String getQop() {
        return qop;
    }
    public void setQop(String qop) {
        this.qop = qop;
    }
    public String getNc() {
        return nc;
    }
    public void setNc(String nc) {
        this.nc = nc;
    }
    public String getCnonce() {
        return cnonce;
    }
    public void setCnonce(String cnonce) {
        this.cnonce = cnonce;
    }
    @Override
    public String toString() {
        return "DigestAuthInfo [username=" + username + ", realm=" + realm + ", nonce=" + nonce + ", uri=" + uri
                + ", response=" + response + ", qop=" + qop + ", nc=" + nc + ", cnonce=" + cnonce + "]";
    }
}
