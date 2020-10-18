package ex01;

/**
 * The interface ProductsHandler is to have segregation in place especially,
 * because they can be impacted by IO exceptions. In the current example we actually do not need to have multiple classes
 * implementation. However we to want to implement the wanted load write segregation,
 * even when we are using a full property driven approach for the other classes. \
 */

public interface ProductsHandler {

    boolean load(String file);

    boolean write(String file);
}
