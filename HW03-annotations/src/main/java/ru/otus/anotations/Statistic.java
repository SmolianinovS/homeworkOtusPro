package ru.otus.anotations;

public class Statistic {
    private int successful = 0;

    public Statistic(int successful, int failed) {
        this.successful = successful;
        this.failed = failed;
    }

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    private int failed = 0;
}
