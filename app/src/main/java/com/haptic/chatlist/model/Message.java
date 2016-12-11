
package com.haptic.chatlist.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message {

    private String body;
    private String username;
    @SerializedName("Name")
    private String name;
    @SerializedName("image-url")
    private String image_url;
    @SerializedName("message-time")
    private String message_time;
    private boolean isFavorite;

    /**
     * 
     * @return
     *     The body
     */
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The image_url
     */
    public String getImage_url() {
        return image_url;
    }

    /**
     * 
     * @param image_url
     *     The image-url
     */
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    /**
     * 
     * @return
     *     The message_time
     */
    public String getMessage_time() {
        return message_time;
    }

    /**
     * 
     * @param message_time
     *     The message-time
     */
    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    /**
     *
     * @return
     *      The Favorite flag
     */
    public boolean isFavorite() {
        return isFavorite;
    }

    /**
     *
     * @param favorite
     *      The Favorite flag
     */
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
