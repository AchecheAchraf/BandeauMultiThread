package bandeau;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {
        // Créer des scénarios différents pour chaque bandeau
        Scenario scenario1 = makeScenario1();
        Scenario scenario2 = makeScenario2();
        Scenario scenario3 = makeScenario3();

        // Créer des bandeaux thread-safe
        var b1 = new ThreadSafeBandeau();
        var b2 = new ThreadSafeBandeau();
        var b3 = new ThreadSafeBandeau();

        System.out.println("CTRL-C pour terminer le programme");

        // Exécuter les scénarios en parallèle sur différents bandeaux
        Thread t1 = new Thread(() -> b1.playScenario(scenario1));
        Thread t2 = new Thread(() -> b2.playScenario(scenario2));
        Thread t3 = new Thread(() -> b3.playScenario(scenario3));

        t1.start();
        t2.start();
        t3.start();

        // Attendre que les threads se terminent avant de rejouer sur b1
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Rejouer un scénario sur b1 (par exemple, scenario1)
        b1.playScenario(scenario1);
    }

    private Scenario makeScenario1() {
        Scenario s = new Scenario();
        s.addEffect(new RandomEffect("Effet aléatoire sur b1", 700), 1);
        s.addEffect(new Rotate("Rotation à droite sur b1", 180, 4000, true), 2);
        return s;
    }

    private Scenario makeScenario2() {
        Scenario s = new Scenario();
        s.addEffect(new TeleType("Texte caractère par caractère sur b2", 100), 1);
        s.addEffect(new Blink("Clignotement sur b2", 200), 5);
        return s;
    }

    private Scenario makeScenario3() {
        Scenario s = new Scenario();
        s.addEffect(new Zoom("Zoom sur b3", 50), 1);
        s.addEffect(new Rainbow("Arc-en-ciel sur b3", 30), 1);
        return s;
    }
}
