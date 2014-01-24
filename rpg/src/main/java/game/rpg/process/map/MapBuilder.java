package game.rpg.process.map;

import game.rpg.process.map.bean.DataBean;
import game.rpg.process.map.bean.ImageBean;
import game.rpg.process.map.bean.LayerBean;
import game.rpg.process.map.bean.MapBean;
import game.rpg.process.map.bean.TilesetBean;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import util.CSV;

public class MapBuilder {

    private static final MapBuilder instance = new MapBuilder();

    private final Digester digester;

    private MapBuilder() {
        digester = new Digester();
        digester.addObjectCreate("map", MapBean.class);
        digester.addSetProperties("map");

        digester.addObjectCreate("map/tileset", TilesetBean.class);
        digester.addSetNext("map/tileset", "setTileset");
        digester.addSetProperties("map/tileset");

        digester.addObjectCreate("map/tileset/image", ImageBean.class);
        digester.addSetNext("map/tileset/image", "setImage");
        digester.addSetProperties("map/tileset/image");

        digester.addObjectCreate("map/layer", LayerBean.class);
        digester.addSetNext("map/layer", "addLayer");
        digester.addSetProperties("map/layer");

        digester.addObjectCreate("map/layer/data", DataBean.class);
        digester.addSetNext("map/layer/data", "setData");
        digester.addSetProperties("map/layer/data");
        digester.addBeanPropertySetter("map/layer/data");
    }

    public static MapBuilder getInstance() {
        return instance;
    }

    public Map parse(String mapFileName) throws IOException, MapParseException {
        File file = new File(mapFileName);

        MapBean mapBean;
        try {
            mapBean = digester.parse(new FileInputStream(file));
        } catch (SAXException e) {
            throw new MapParseException(e);
        }

        // width, height
        int width  = Integer.parseInt(mapBean.getWidth());
        int height = Integer.parseInt(mapBean.getHeight());

        // tileSize
        int tilewidth  = Integer.parseInt(mapBean.getTilewidth());
        int tileheight = Integer.parseInt(mapBean.getTileheight());
        if (tilewidth != tileheight)
            throw new MapParseException("tilewidth != tileheight");
        int tileSize = tilewidth;

        // tileSet
        String source = mapBean.getTileset().getImage().getSource();
        String path = file.getParent() + File.separator + source;
        Image tileSet = new ImageIcon(path).getImage();

        // tileSetColumn
        int tileSetColumn = tileSet.getWidth(null) / tileSize;

        // background, middleground, foreground, collision
        List<LayerBean> layerList = mapBean.getLayerList();
        if (layerList.size() != 4)
            throw new MapParseException("layerList != 4");
        int[][] background   = parseLayer(layerList.get(0).getData().getData(), width, height);
        int[][] middleground = parseLayer(layerList.get(1).getData().getData(), width, height);
        int[][] foreground   = parseLayer(layerList.get(2).getData().getData(), width, height);
        int[][] collision    = parseLayer(layerList.get(3).getData().getData(), width, height);

        return new Map()
                .setWidth(width)
                .setHeight(height)
                .setTileSize(tileSize)
                .setTileSet(tileSet)
                .setTileSetColumn(tileSetColumn)
                .setBackground(background)
                .setMiddleground(middleground)
                .setForeground(foreground)
                .setCollision(collision);
    }

    private int[][] parseLayer(String data, int width, int height) throws IOException {
        Reader r = new StringReader(data);
        List<String[]> table = CSV.read(r);

        int[][] layer = new int[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                layer[y][x] = Integer.parseInt(table.get(y)[x]);
        return layer;
    }

}
