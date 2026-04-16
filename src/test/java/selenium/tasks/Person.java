package selenium.tasks;

import java.util.Objects;

public class Person {
    private final String name;
    private final String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() { return name; }
    public String getJob() { return job; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(job, person.job);
    }

    @Override
    public int hashCode() { return Objects.hash(name, job); }
}
