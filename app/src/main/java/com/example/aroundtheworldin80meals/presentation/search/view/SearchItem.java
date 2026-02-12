package com.example.aroundtheworldin80meals.presentation.search.view;

public class SearchItem {
    public SearchItemType type;
    public String name;
    public String thumb;


    public SearchItem(SearchItemType type, String name,String thumb) {
        this.type = type;
        this.name = name;
        this.thumb = thumb;

    }

    public SearchItemType getType() {
        return type;
    }

    public void setType(SearchItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
