package com.example.task3kg;

import com.example.task3kg.TriangulatedModel;
import com.example.task3kg.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        // Указываем путь к файлу OBJ
        Path fileName = Path.of("C:\\Users\\Home\\Downloads\\CGVSU-main\\CGVSU-main\\3DModels\\SimpleModelsForReaderTests\\Torus.obj");
        String fileContent = Files.readString(fileName);

        // Загружаем модель
        System.out.println("Loading model ...");
        TriangulatedModel model = new TriangulatedModel();
        model.vertices = ObjReader.read(fileContent).vertices;
        model.textureVertices = ObjReader.read(fileContent).textureVertices;
        model.normals = ObjReader.read(fileContent).normals;
        model.polygons = ObjReader.read(fileContent).polygons;

        // Выводим информацию о загруженной модели
        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons before triangulation: " + model.polygons.size());

        // Выполняем триангуляцию
        System.out.println("Triangulating model ...");
        model.triangulate();

        // Выводим обновленную информацию о модели
        System.out.println("Polygons after triangulation: " + model.polygons.size());
    }
}
