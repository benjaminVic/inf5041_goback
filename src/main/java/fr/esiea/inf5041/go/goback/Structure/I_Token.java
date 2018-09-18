package fr.esiea.inf5041.go.goback.Structure;

public interface I_Token {

    enum Color {
        WHITE(0, 'W'),
        BLACK(0, 'B'),
        EMPTY(1, '+'),
        OUTOFBOARD(0, 'O');

        private final int degreeOfFreedom;
        private final char boardValue;

        Color (int DegreeOfFreedom, char boardValue) {
            this.degreeOfFreedom = DegreeOfFreedom;
            this.boardValue = boardValue;
        }

        public final int getDegreeOfFreedom() {
            return degreeOfFreedom;
        }

        public final char getBoardValue(){
            return this.boardValue;
        }
    }

}
