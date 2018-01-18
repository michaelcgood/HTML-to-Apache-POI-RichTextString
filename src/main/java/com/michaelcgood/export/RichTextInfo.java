package com.michaelcgood.export;

import org.springframework.cache.annotation.Cacheable;

public class RichTextInfo {
    private int startIndex;
    private int endIndex;
    private STYLES fontStyle;
    private String fontValue;

    public RichTextInfo(int startIndex, int endIndex, STYLES fontStyle) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.fontStyle = fontStyle;
    }

    public RichTextInfo(int startIndex, int endIndex, STYLES fontStyle,
            String fontValue) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.fontStyle = fontStyle;
        this.fontValue = fontValue;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public STYLES getFontStyle() {
        return fontStyle;
    }

    @Cacheable("fontstyle")
    public void setFontStyle(STYLES fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontValue() {
        return fontValue;
    }

    public void setFontValue(String fontValue) {
        this.fontValue = fontValue;
    }

    public boolean isValid() {
        return (startIndex != -1 && endIndex != -1 && endIndex >= startIndex);
    }

    @Override
    public String toString() {
        return "RichTextInfo [startIndex=" + startIndex + ", endIndex="
                + endIndex + ", fontStyle=" + fontStyle + ", fontValue="
                + fontValue + "]";
    }
}