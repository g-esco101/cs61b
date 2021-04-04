import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] strings = new GuitarString[37];

    public static void main(String[] args) {
        /* create the guitar strings, for concert A and C */
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(frequency(i));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    continue;
                }
                strings[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < strings.length; i++) {
                sample += strings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < strings.length; i++) {
                strings[i].tic();
            }
        }
    }

    private static double frequency(int index) {
        double exponent = (double) (index - 24) / 12;
        return 440 * Math.pow(2, exponent);
    }
}
