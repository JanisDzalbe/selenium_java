package selenium.customDataTable;

public class PersonData {
    String name;
    String job;

    public PersonData(String name, String job) {
        this.name = name;
        this.job = job;
    }
    public String getName() {
        return name;
    }
    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return String.format("{Name: %s, Job: %s}", name, job);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonData that = (PersonData) obj;
        return name.equals(that.name) && job.equals(that.job);
    }
}
