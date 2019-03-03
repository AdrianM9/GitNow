import utils.Context;

/**
 * The entry point of the program.
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        Context context = Context.getInstance();

        context.init(input, output);
        context.run();
    }
}
