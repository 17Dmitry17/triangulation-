package com.example.task3kg;

import com.example.task3kg.model.Model;
import com.example.task3kg.model.Polygon;

import java.util.ArrayList;

public class TriangulatedModel extends Model {

    public void triangulate() {
        ArrayList<Polygon> newPolygons = new ArrayList<>();

        for (Polygon polygon : this.polygons) {
            ArrayList<Integer> vertexIndices = polygon.getVertexIndices();
            ArrayList<Integer> textureVertexIndices = polygon.getTextureVertexIndices();
            ArrayList<Integer> normalIndices = polygon.getNormalIndices();

            if (vertexIndices.size() > 3) {
                for (int i = 1; i < vertexIndices.size() - 1; i++) {
                    Polygon triangle = new Polygon();

                    ArrayList<Integer> triangleVertexIndices = new ArrayList<>();
                    triangleVertexIndices.add(vertexIndices.get(0));
                    triangleVertexIndices.add(vertexIndices.get(i));
                    triangleVertexIndices.add(vertexIndices.get(i + 1));
                    triangle.setVertexIndices(triangleVertexIndices);

                    if (!textureVertexIndices.isEmpty()) {
                        ArrayList<Integer> triangleTextureIndices = new ArrayList<>();
                        triangleTextureIndices.add(textureVertexIndices.get(0));
                        triangleTextureIndices.add(textureVertexIndices.get(i));
                        triangleTextureIndices.add(textureVertexIndices.get(i + 1));
                        triangle.setTextureVertexIndices(triangleTextureIndices);
                    }

                    if (!normalIndices.isEmpty()) {
                        ArrayList<Integer> triangleNormalIndices = new ArrayList<>();
                        triangleNormalIndices.add(normalIndices.get(0));
                        triangleNormalIndices.add(normalIndices.get(i));
                        triangleNormalIndices.add(normalIndices.get(i + 1));
                        triangle.setNormalIndices(triangleNormalIndices);
                    }

                    newPolygons.add(triangle);
                }
            } else {
                newPolygons.add(polygon);
            }
        }

        this.polygons = newPolygons;
    }
}
