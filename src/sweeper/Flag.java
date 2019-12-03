package sweeper;

class Flag {
    Matrix flagMap;

    int countOfClosedBoxes;

    int countOfFlagedBoxes;

    public int getCountOfFlaged() {
        return countOfFlagedBoxes;
    }

    void start(){
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }
    Box get (Coord coord){
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;

    }

    public void setFlagetToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
        countOfFlagedBoxes++;                        ///////
    }

    public void toggelFlagetToBox(Coord coord) {
        switch (flagMap.get(coord)){
            case FLAGED:setClosedToBox(coord);break;
            case CLOSED:setFlagetToBox(coord);break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord , Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void serNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
