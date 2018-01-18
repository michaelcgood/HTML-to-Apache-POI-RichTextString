package com.michaelcgood.export;

import java.util.Map;

import org.apache.poi.ss.usermodel.Font;
import org.springframework.cache.annotation.Cacheable;

public class RichTextDetails {
    private String richText;
    private Map<Integer, Font> fontMap;

    public RichTextDetails(String richText,
            Map<Integer, Font> fontMap2) {
        this.richText = richText;
        this.fontMap = fontMap2;
    }

    public String getRichText() {
        return richText;
    }
    public void setRichText(String richText) {
        this.richText = richText;
    }
    public Map<Integer, Font> getFontMap() {
        return fontMap;
    }

    public void setFontMap(Map<Integer, Font> fontMap) {
        this.fontMap = fontMap;
    }
    
    @Override
    public int hashCode() {
     
        // The goal is to have a more efficient hashcode than standard one.
        return richText.hashCode();
    }
}