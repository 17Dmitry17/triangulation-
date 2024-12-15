package com.example.task3kg;

import com.example.task3kg.TriangulatedModel;
import com.example.task3kg.objreader.ObjReader;
import com.example.task3kg.objwriter.ObjWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("C:\\Users\\Home\\Downloads\\CGVSU-main\\CGVSU-main\\3DModels\\SimpleModelsForReaderTests\\Torus.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Загрузка ...");
        System.out.println("-----------------------------");
        TriangulatedModel model = new TriangulatedModel();
        model.vertices = ObjReader.read(fileContent).vertices;
        model.textureVertices = ObjReader.read(fileContent).textureVertices;
        model.normals = ObjReader.read(fileContent).normals;
        model.polygons = ObjReader.read(fileContent).polygons;

        System.out.println("Вершины: " + model.vertices.size());
        System.out.println("Вершины текстуры: " + model.textureVertices.size());
        System.out.println("Нормали: " + model.normals.size());
        System.out.println("Полигоны : " + model.polygons.size());

        System.out.println("-----------------------------");
        System.out.println("Триангуляция ...");
        System.out.println("-----------------------------");
        model.triangulate();
        System.out.println("Вершины: " + model.vertices.size());
        System.out.println("Вершины текстуры: " + model.textureVertices.size());
        System.out.println("Нормали: " + model.normals.size());
        System.out.println("Полигоны : " + model.polygons.size());
        System.out.println("-----------------------------");
        System.out.println("Сохранение ...");
        ObjWriter writer = new ObjWriter();
        writer.write(model, "output.obj");

        System.out.println("Сохранено в output.obj");
    }
}