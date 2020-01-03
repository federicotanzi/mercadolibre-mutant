package com.mercadolibre.mutante.detector;

public class PositionContext {

    private int subIndex;
    private int row;
    private int column;
    private char lastChar;
    private char currentChar;
    private int safeIndex;
    private int size;

    public PositionContext(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public PositionContext(int row, int column, int subIndex) {
        this.subIndex = subIndex;
        this.row = row;
        this.column = column;
    }

    public PositionContext(int subIndex, int row, int column, char lastChar, char currentChar, int safeIndex, int size) {
        this.subIndex = subIndex;
        this.row = row;
        this.column = column;
        this.lastChar = lastChar;
        this.currentChar = currentChar;
        this.safeIndex = safeIndex;
        this.size = size;
    }

    public int getSubIndex() {
        return subIndex;
    }

    public void setSubIndex(int subIndex) {
        this.subIndex = subIndex;
    }

    public void addSubIndex() {
        this.subIndex = this.subIndex + 1;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void addRow() {
        this.row = this.row + 1;
    }

    public void subtractRow() {
        this.row = this.row - 1;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void addColumn() {
        this.column = this.column + 1;
    }

    public char getLastChar() {
        return lastChar;
    }

    public void setLastChar(char lastChar) {
        this.lastChar = lastChar;
    }

    public char getCurrentChar() {
        return currentChar;
    }

    public void setCurrentChar(char currentChar) {
        this.currentChar = currentChar;
    }

    public int getSafeIndex() {
        return safeIndex;
    }

    public void setSafeIndex(int safeIndex) {
        this.safeIndex = safeIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
