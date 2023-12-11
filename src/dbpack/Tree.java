package dbpack;

public class Tree {
    private int treeId;
    private int requestId;
    private double height;
    private double distance;
    private String address;
    private double size;

    // Constructor
    public Tree() {
    }
    public Tree(int treeId, int requestId, double height, double distance, String address, double size) {
        this.treeId = treeId;
        this.requestId = requestId;
        this.height = height;
        this.distance = distance;
        this.address = address;
        this.size = size;
    }

    // Getters and Setters
    public int getTreeId() {
        return treeId;
    }

    public void setTreeId(int treeId) {
        this.treeId = treeId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
