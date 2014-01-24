package game.rpg.process.map.bean;

public class TilesetBean {

    private String firstgid;
    private String name;
    private String tilewidth;
    private String tileheight;
    private ImageBean image;

    public String getFirstgid() {
        return firstgid;
    }

    public void setFirstgid(String firstgid) {
        this.firstgid = firstgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTilewidth() {
        return tilewidth;
    }

    public void setTilewidth(String tilewidth) {
        this.tilewidth = tilewidth;
    }

    public String getTileheight() {
        return tileheight;
    }

    public void setTileheight(String tileheight) {
        this.tileheight = tileheight;
    }

    public ImageBean getImage() {
        return image;
    }

    public void setImage(ImageBean image) {
        this.image = image;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TilesetBean [firstgid=");
        builder.append(firstgid);
        builder.append(", name=");
        builder.append(name);
        builder.append(", tilewidth=");
        builder.append(tilewidth);
        builder.append(", tileheight=");
        builder.append(tileheight);
        builder.append(", image=");
        builder.append(image);
        builder.append("]");
        return builder.toString();
    }

}
