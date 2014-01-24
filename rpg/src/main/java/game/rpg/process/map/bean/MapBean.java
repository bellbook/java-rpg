package game.rpg.process.map.bean;

import java.util.ArrayList;
import java.util.List;

public class MapBean {

    private String version;
    private String orientation;
    private String width;
    private String height;
    private String tilewidth;
    private String tileheight;
    private TilesetBean tileset;
    private List<LayerBean> layerList = new ArrayList<LayerBean>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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

    public TilesetBean getTileset() {
        return tileset;
    }

    public void setTileset(TilesetBean tileset) {
        this.tileset = tileset;
    }

    public List<LayerBean> getLayerList() {
        return layerList;
    }

    public void addLayer(LayerBean layer) {
        this.layerList.add(layer);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MapBean [version=");
        builder.append(version);
        builder.append(", orientation=");
        builder.append(orientation);
        builder.append(", width=");
        builder.append(width);
        builder.append(", height=");
        builder.append(height);
        builder.append(", tilewidth=");
        builder.append(tilewidth);
        builder.append(", tileheight=");
        builder.append(tileheight);
        builder.append(", tileset=");
        builder.append(tileset);
        builder.append(", layerList=");
        builder.append(layerList);
        builder.append("]");
        return builder.toString();
    }

}
