package edu.neu.madcourse.numad21su_shailigandhi;


public class ItemCard{

    private final int imageSource;
    private final String itemName;
    private final String itemLink;

    public ItemCard(int imageSource, String itemName, String itemLink) {
        this.imageSource = imageSource;
        this.itemName = itemName;
        this.itemLink = itemLink;
    }

    public int getImageSource() {
        return imageSource;
    }

    public String getItemLink() {
        return itemLink;
    }

    public String getItemName() {
        return itemName;
    }

}