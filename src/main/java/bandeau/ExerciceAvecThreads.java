package bandeau;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {
        Scenario s = makeScenario();

        // On crée des bandeaux thread-safe
        var b1 = new ThreadSafeBandeau();
        var b2 = new ThreadSafeBandeau();
        var b3 = new ThreadSafeBandeau();

        System.out.println("CTRL-C pour terminer le programme");

        // Exécuter les scénarios en parallèle sur différents bandeaux
        Thread t1 = new Thread(() -> b1.playScenario(s));
        Thread t2 = new Thread(() -> b2.playScenario(s));
        Thread t3 = new Thread(() -> b3.playScenario(s));

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

        // Rejouer le scénario sur b1
        b1.playScenario(s);
    }

    private Scenario makeScenario() {
        Scenario s = new Scenario();
        s.addEffect(new RandomEffect("Le jeu du pendu", 700), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 4000, true), 2);
        return s;
    }
}
