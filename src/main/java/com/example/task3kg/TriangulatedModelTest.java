package com.example.task3kg;

import com.example.task3kg.model.Polygon;
import com.example.task3kg.TriangulatedModel;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TriangulatedModelTest {

    @Test
    public void testTriangulationOfQuad() {
        TriangulatedModel model = new TriangulatedModel();
        Polygon quad = new Polygon();
        quad.setVertexIndices(new ArrayList<>(java.util.List.of(0, 1, 2, 3)));
        model.polygons.add(quad);
        model.triangulate();
        assertEquals(2, model.polygons.size());

        ArrayList<Integer> triangle1 = model.polygons.get(0).getVertexIndices();
        ArrayList<Integer> triangle2 = model.polygons.get(1).getVertexIndices();

        assertEquals(java.util.List.of(0, 1, 2), triangle1);
        assertEquals(java.util.List.of(0, 2, 3), triangle2);
    }

    @Test
    public void testTriangulationOfTriangle() {
        TriangulatedModel model = new TriangulatedModel();
        Polygon triangle = new Polygon();
        triangle.setVertexIndices(new ArrayList<>(java.util.List.of(0, 1, 2)));
        model.polygons.add(triangle);
        model.triangulate();
        assertEquals(1, model.polygons.size());

        ArrayList<Integer> triangleIndices = model.polygons.get(0).getVertexIndices();
        assertEquals(java.util.List.of(0, 1, 2), triangleIndices);
    }

    @Test
    public void testEmptyModel() {
        TriangulatedModel model = new TriangulatedModel();
        model.triangulate();
        assertTrue(model.polygons.isEmpty());
    }

    @Test
    public void testPolygonWithDuplicateVertexIndices() {
        TriangulatedModel model = new TriangulatedModel();
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(java.util.List.of(0, 0, 1, 2)));
        model.polygons.add(polygon);
        model.triangulate();
        assertEquals(2, model.polygons.size());
        Polygon triangle1 = model.polygons.get(0);
        Polygon triangle2 = model.polygons.get(1);
        assertEquals(java.util.List.of(0, 0, 1), triangle1.getVertexIndices());
        assertEquals(java.util.List.of(0, 1, 2), triangle2.getVertexIndices());
    }

}
