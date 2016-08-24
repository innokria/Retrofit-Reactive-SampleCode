package com.cd.com.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kumar0044q on 8/11/2016.
 */
public class Deal {






    @SerializedName("AgentId")
    @Expose
    private String agentId;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Subtitle")
    @Expose
    private String subtitle;
    @SerializedName("Img")
    @Expose
    private String img;
    @SerializedName("Link")
    @Expose
    private String link;
    @SerializedName("Status")
    @Expose
    private Object status;

    /**
     *
     * @return
     * The agentId
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     *
     * @param agentId
     * The AgentId
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     *
     * @param subtitle
     * The Subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     *
     * @return
     * The img
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img
     * The Img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The Link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The status
     */
    public Object getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The Status
     */
    public void setStatus(Object status) {
        this.status = status;
    }


    @Override
    public String toString(){
        return(subtitle);
    }


}