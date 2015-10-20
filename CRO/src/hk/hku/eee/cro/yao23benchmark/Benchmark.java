package hk.hku.eee.cro.yao23benchmark;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Benchmark {

    static final public int NUM_TEST_FUNC = 23;
    static final public String[] test_func_class_names = {
        "F01", "F02", "F03", "F04", "F05", "F06", "F07", "F08", "F09", "F10",
        "F11", "F12", "F13", "F14", "F15", "F16", "F17", "F18", "F19", "F20",
        "F21", "F22", "F23"
    };
    static final public ClassLoader loader = ClassLoader.getSystemClassLoader();
    static final public Random r = new Random();

    public Benchmark() {
    }

    public Objective ObjFcnFactory(int func_num) {
        Objective returnFunc = null;
        try {
            returnFunc = (Objective) loader.loadClass("hk.hku.eee.cro.yao23benchmark."
                    + test_func_class_names[func_num - 1]).getConstructor().newInstance(
                    new Object[]{});
        } catch (InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException |
                ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Benchmark.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (returnFunc);
    }
}
