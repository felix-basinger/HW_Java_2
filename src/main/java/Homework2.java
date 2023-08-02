

import java.util.ArrayList;
import java.util.List;

interface PeopleBehaviour {
    void setMakeOrder();

    void setTakeOrder();

    boolean isMakeOrder = false;
    boolean isTakeOrder = false;
}

interface QueueBehaviour {
    void takeInQueue(People people);

    void takeOrders(People people);

    void giveOrders(People people);

    void releaseFromQueue(People people);
}

interface MarketBehaviour {
    void acceptToMarket(People people);

    void releaseFromMarket(People people);

    void update();

}


abstract class People implements PeopleBehaviour {
    protected String name;
    protected boolean isMakeOrder;
    protected boolean isTakeOrder;

    public People(String name) {
        this.name = name;
        isMakeOrder = false;
        isTakeOrder = false;
    }

    public String getName() {
        return name;
    }

    public abstract void setMakeOrder(boolean b);

    public abstract void setTakeOrder(boolean b);
}

class Human extends People {
    public Human(String name) {
        super(name);
    }

    @Override
    public void setMakeOrder(boolean b) {
        this.isMakeOrder = b;
    }

    @Override
    public void setTakeOrder(boolean b) {
        this.isTakeOrder = b;
    }

    @Override
    public void setMakeOrder() {

    }

    @Override
    public void setTakeOrder() {

    }
}


class Market implements MarketBehaviour, QueueBehaviour {

    private final List<People> queue;
    private final List<People> market;
    public Market(){
        queue = new ArrayList<>();
        market = new ArrayList<>();
    }

    @Override
    public void takeInQueue(People people) {
        queue.add(people);
        System.out.println(people.getName() + " Становится в очередь");
    }

    @Override
    public void takeOrders(People people) {
        people.setTakeOrder(true);
        System.out.println(people.getName() + " получает заказ");
    }

    @Override
    public void giveOrders(People people) {
        people.setMakeOrder(true);
        System.out.println(people.getName() + " делает заказ");

    }

    @Override
    public void releaseFromQueue(People people) {
        queue.remove(people);
        System.out.println(people.getName() + " покидает очередь");

    }

    @Override
    public void acceptToMarket(People people) {
        market.add(people);
        System.out.println(people.getName() + " входит в магазин");

    }

    @Override
    public void releaseFromMarket(People people) {
        market.remove(people);
        System.out.println(people.getName() + " покидает магазин");

    }

    @Override
    public void update() {
        for (People people : market) {
            System.out.printf(people.getName() + ", ");
        }
        System.out.println("Сейчас в магазине");
    }
}

public class Homework2 {
    public static void main(String[] args) {
        Human human = new Human("Alex");
        Human men = new Human("Ben");
        Market market = new Market();
        market.acceptToMarket(human);
        market.giveOrders(human);
        market.takeOrders(human);
        market.acceptToMarket(men);
        market.releaseFromMarket(human);
        market.update();
    }
}
