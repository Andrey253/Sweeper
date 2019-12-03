package sweeper;

import java.util.Random;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    public Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombCount();
    }
    void start () {
        bombMap = new Matrix(Box.ZERO);

        for (int j = 0; j <totalBombs; j++){
            placebomb();
        }
    }

    Box get (Coord coord){
        return bombMap.get(coord);
    }

    private void fixBombCount(){
        int maxBombs = (Ranges.getSize().x * Ranges.getSize().y)/2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placebomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (bombMap.get(coord) == Box.BOMB)
                continue;
            bombMap.set(coord,Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }


    }
    private void incNumbersAroundBomb(Coord coord){
        for (Coord arround : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(arround))
                bombMap.set(arround, bombMap.get(arround).getnextNumberBox());

    }

    int getTotalBombs() {
        return totalBombs;
    }
}
