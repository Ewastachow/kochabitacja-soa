package automa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.nio.file.Path;

public class Image {
    @JsonIgnore
    private int id;
    private String name;
    @JsonIgnore
    private Path path;
    @JsonIgnore
    private byte[] source;

    public Image() {
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
