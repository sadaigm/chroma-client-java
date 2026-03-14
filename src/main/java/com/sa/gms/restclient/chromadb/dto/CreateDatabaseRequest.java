package com.sa.gms.restclient.chromadb.dto;

/**
 * Request DTO for creating a new database in Chroma DB.
 */
public class CreateDatabaseRequest {

    private String name;

    /**
     * Default constructor.
     */
    public CreateDatabaseRequest() {
    }

    /**
     * Constructor with name.
     *
     * @param name the database name
     */
    public CreateDatabaseRequest(String name) {
        this.name = name;
    }

    /**
     * Gets the database name.
     *
     * @return the database name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the database name.
     *
     * @param name the database name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateDatabaseRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}