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

        System.out.println("Loading model ...");
        TriangulatedModel model = new TriangulatedModel();
        model.vertices = ObjReader.read(fileContent).vertices;
        model.textureVertices = ObjReader.read(fileContent).textureVertices;
        model.normals = ObjReader.read(fileContent).normals;
        model.polygons = ObjReader.read(fileContent).polygons;

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons before triangulation: " + model.polygons.size());

        System.out.println("Triangulating model ...");
        model.triangulate();
        System.out.println("Polygons after triangulation: " + model.polygons.size());

        System.out.println("Saving model ...");
        ObjWriter writer = new ObjWriter();
        writer.write(model, "output.obj");

        System.out.println("Model saved to output.obj");
    }
}