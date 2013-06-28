import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import javax.script.*;

public class HelloWorldScript {

    public static void main(String... args) {
        ScriptEngineManager scm = new ScriptEngineManager();

        scm.getEngineFactories().forEach(engineFactory -> {
            System.out.println(String.format("Engine Factory: %s", engineFactory));
            System.out.println(String.format("\tLanguage: %s", engineFactory.getLanguageName()));
            engineFactory.getExtensions().forEach(extension -> {
                System.out.println(String.format("\tExtension %s", extension));
            });

            engineFactory.getNames().forEach(name -> {
                System.out.println("\tName: " + name);
            });
        });

        ScriptEngine jsEngine = scm.getEngineByName("nashorn");

        try {
            jsEngine.eval("print('Hello World');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try (Reader helloWorldScript = new InputStreamReader(HelloWorldScript.class.getResourceAsStream("/HelloWorld.js"))) {
          Bindings bindings = new SimpleBindings();

          List<Integer> blah = Arrays.asList(1, 2, 3, 4, 5, 6);
          bindings.put("blah", blah);
          jsEngine.eval(helloWorldScript, bindings);

        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }
    }
}
