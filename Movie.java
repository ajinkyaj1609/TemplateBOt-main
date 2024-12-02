class Movie {
    private String name;
    private String ageRating;
    private double price;
    private int length;
    private String resolution;

    public Movie(String name, String ageRating, double price, int length, String resolution) {
        this.name = name;
        this.ageRating = ageRating;
        this.price = price;
        this.length = length;
        this.resolution = resolution;
    }
    

    public String getName() {
        return name;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public double getPrice() {
        return price;
    }

    public int getLength() {
        return length;
    }

    public String getResolution() {
        return resolution;
    }
}
