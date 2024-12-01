package com.example.task3kg;

import com.example.task3kg.model.Polygon;
import com.example.task3kg.TriangulatedModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TriangulatedModelTest {

    @Test
    void testTriangulationOfQuad() {
        // Исходный четырёхугольник
        TriangulatedModel model = new TriangulatedModel();

        Polygon quad = new Polygon();
        quad.setVertexIndices(new ArrayList<>(java.util.List.of(0, 1, 2, 3)));
        model.polygons.add(quad);

        // Выполняем триангуляцию
        model.triangulate();

        // Проверяем, что получили 2 треугольника
        assertEquals(2, model.polygons.size());

        // Проверяем, что треугольники имеют правильные индексы
        ArrayList<Integer> triangle1 = model.polygons.get(0).getVertexIndices();
        ArrayList<Integer> triangle2 = model.polygons.get(1).getVertexIndices();

        assertEquals(java.util.List.of(0, 1, 2), triangle1);
        assertEquals(java.util.List.of(0, 2, 3), triangle2);
    }

    @Test
    void testTriangulationOfTriangle() {
        // Исходный треугольник
        TriangulatedModel model = new TriangulatedModel();

        Polygon triangle = new Polygon();
        triangle.setVertexIndices(new ArrayList<>(java.util.List.of(0, 1, 2)));
        model.polygons.add(triangle);

        // Выполняем триангуляцию
        model.triangulate();

        // Проверяем, что триангуляция не изменила количество полигонов
        assertEquals(1, model.polygons.size());

        // Проверяем, что индексы остались прежними
        ArrayList<Integer> triangleIndices = model.polygons.get(0).getVertexIndices();
        assertEquals(java.util.List.of(0, 1, 2), triangleIndices);
    }

    @Test
    void testTriangulationWithTextureAndNormalIndices() {
        // Исходный четырёхугольник с текстурными и нормальными индексами
        TriangulatedModel model = new TriangulatedModel();

        Polygon quad = new Polygon();
        quad.setVertexIndices(new ArrayList<>(java.util.List.of(0, 1, 2, 3)));
        quad.setTextureVertexIndices(new ArrayList<>(java.util.List.of(10, 11, 12, 13)));
        quad.setNormalIndices(new ArrayList<>(java.util.List.of(20, 21, 22, 23)));
        model.polygons.add(quad);

        // Выполняем триангуляцию
        model.triangulate();

        // Проверяем, что получили 2 треугольника
        assertEquals(2, model.polygons.size());

        // Проверяем правильность первого треугольника
        Polygon triangle1 = model.polygons.get(0);
        assertEquals(java.util.List.of(0, 1, 2), triangle1.getVertexIndices());
        assertEquals(java.util.List.of(10, 11, 12), triangle1.getTextureVertexIndices());
        assertEquals(java.util.List.of(20, 21, 22), triangle1.getNormalIndices());

        // Проверяем правильность второго треугольника
        Polygon triangle2 = model.polygons.get(1);
        assertEquals(java.util.List.of(0, 2, 3), triangle2.getVertexIndices());
        assertEquals(java.util.List.of(10, 12, 13), triangle2.getTextureVertexIndices());
        assertEquals(java.util.List.of(20, 22, 23), triangle2.getNormalIndices());
    }

    @Test
    void testEmptyModel() {
        // Пустая модель
        TriangulatedModel model = new TriangulatedModel();

        // Выполняем триангуляцию
        model.triangulate();

        // Убедимся, что список полигонов пустой
        assertTrue(model.polygons.isEmpty());
    }

    @Test
    void testMultiplePolygons() {
        // Модель с несколькими полигонами
        TriangulatedModel model = new TriangulatedModel();

        Polygon triangle = new Polygon();
        triangle.setVertexIndices(new ArrayList<>(java.util.List.of(0, 1, 2)));

        Polygon quad = new Polygon();
        quad.setVertexIndices(new ArrayList<>(java.util.List.of(3, 4, 5, 6)));

        model.polygons.add(triangle);
        model.polygons.add(quad);

        // Выполняем триангуляцию
        model.triangulate();

        // Проверяем, что в результате 3 треугольника
        assertEquals(3, model.polygons.size());

        // Проверяем треугольники
        Polygon resultTriangle = model.polygons.get(0);
        assertEquals(java.util.List.of(0, 1, 2), resultTriangle.getVertexIndices());

        Polygon resultTriangle1 = model.polygons.get(1);
        assertEquals(java.util.List.of(3, 4, 5), resultTriangle1.getVertexIndices());

        Polygon resultTriangle2 = model.polygons.get(2);
        assertEquals(java.util.List.of(3, 5, 6), resultTriangle2.getVertexIndices());
    }
}
