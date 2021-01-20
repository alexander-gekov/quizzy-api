package com.quizzy.quizzy.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileInfoTest {

    private FileInfo fileInfoUnderTest;

    @Before
    public void setUp() {
        fileInfoUnderTest = new FileInfo("name", "url");
    }

    @Test
    public void testGetName() {
        FileInfo fileInfo = new FileInfo("Name", "Url");

        assertEquals("Name", fileInfo.getName());
    }

    @Test
    public void testGetURL() {
        FileInfo fileInfo = new FileInfo("Name", "Url");

        assertEquals("Url", fileInfo.getUrl());
    }

    @Test
    public void testSetName() {
        FileInfo fileInfo = new FileInfo("Name", "Url");
        fileInfo.setName("name2");
        assertEquals("name2", fileInfo.getName());
    }

    @Test
    public void testSetURL() {
        FileInfo fileInfo = new FileInfo("Name", "Url");
        fileInfo.setUrl("url2");
        assertEquals("url2", fileInfo.getUrl());
    }
}
