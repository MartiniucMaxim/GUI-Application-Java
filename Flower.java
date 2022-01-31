package Flower;

public class Flower {
    public String name = "";
    public FlowerColor color = FlowerColor.Red;
    public FlowerType type = FlowerType.Decorative;
    public int count = 1;
    public double price = 0;

    @Override
    public String toString() {
        return name+"\n"+color+"\n"+type+"\n"+count+"\n"+price;
    }
    public void refreshFields(String name_in,
                              FlowerColor color_in,
                              FlowerType type_in,
                              int count_in,
                              double price_in
                              ){
        name = name_in;
        color = color_in;
        type = type_in;
        count=count_in;
        price=price_in;
    }
}
