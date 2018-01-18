package com.michaelcgood.export;

public class TagInfo {
    private String tagName;
    private String style;
    private int tagType;

    public TagInfo(String tagName, String style, int tagType) {
        this.tagName = tagName;
        this.style = style;
        this.tagType = tagType;
    }

    public TagInfo(String tagName, int tagType) {
        this.tagName = tagName;
        this.tagType = tagType;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    
    @Override
    public int hashCode() {
     
        // The goal is to have a more efficient hashcode than standard one.
        return tagName.hashCode();
    }

    @Override
    public String toString() {
        return "TagInfo [tagName=" + tagName + ", style=" + style
                + ", tagType=" + tagType + "]";
    }
}
