package com.example.demo;

import java.util.Objects;

public class DemoResponse {
    private String name;
    public DemoResponse() {}

    public DemoResponse(String name) {
        this.name =name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoResponse that = (DemoResponse) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
