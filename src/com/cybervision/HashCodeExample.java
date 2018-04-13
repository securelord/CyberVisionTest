package com.cybervision;

//It is probably necessary to process the multiplication condition of the ranges and not create the object if this condition is not satisfied.
//It is also possible to pass ranges through the constructor, but then it is incorrect to call them constants
public class HashCodeExample {

    private static final int A1 = 0;

    private static final int A2 = 0;

    private static final int B1 = 0;

    private static final int B2 = 0;

    private static final int C1 = 0;

    private static final int C2 = 0;

    private int a;

    private int b;

    private int c;

    public HashCodeExample(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int hashCode() {
        return (a - A1)
                + (b - B1) * (A2 - A1 + 1)
                + (c - C1) * (B2 - B1 + 1) * (A2 - A1 + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HashCodeExample hashCodeExample = (HashCodeExample) o;
        return a == hashCodeExample.a &&
                b == hashCodeExample.b &&
                c == hashCodeExample.c;
    }
}
