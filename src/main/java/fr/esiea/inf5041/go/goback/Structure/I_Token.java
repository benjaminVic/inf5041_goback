package fr.esiea.inf5041.go.goback.Structure;

public interface I_Token {

    enum Color {
        WHITE(0),
        BLACK(0),
        EMPTY(1),
        OUTOFBOARD(0);

        private final int DegreeOfFreedom;

        Color (int DegreeOfFreedom) {
            this.DegreeOfFreedom = DegreeOfFreedom;
        }

        public final int getDegreeOfFreedom() {
            return DegreeOfFreedom;
        }
    }

}
