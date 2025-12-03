package pages;

public class Person {
    private String name;
    private String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() { return name; }
    public String getJob()  { return job; }

    @Override
    public String toString() {
        return name + " – " + job;
    }
}
