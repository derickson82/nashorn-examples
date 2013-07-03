import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: Dan
 * Date: 6/27/13
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
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

        ScriptEngine jsEngine = scm.getEngineByName("js");

        try {
            jsEngine.eval("print('Hello World');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try (Reader helloWorldScript = new FileInputStream("HelloWorld.js")) {
            jsEngine.eval(helloWorldScript);
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }
    }
}
