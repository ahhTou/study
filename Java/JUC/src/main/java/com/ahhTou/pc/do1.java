package com.ahhTou.pc;

public class do1 {
    public static void main(String[] args) {
        People people1 = new People();
        People people2 = new People();


        if (!people1.equals(people2)) {
            Me me = new Me();
            me.toDoWithAnger(true);
        }

    }
}

class People {
    public void toDo() {

    }
}

class Me extends People {
    public void toDoWithAnger(Boolean isAnger) {
        if (isAnger) System.out.println("2天只睡了2、3个小时");
    }
}
