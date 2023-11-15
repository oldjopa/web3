package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("mapper")
@SessionScoped
class Mapper implements Serializable {
    private String currentPage = "clock";

    public String remap(String s) {
        currentPage = s;
        return currentPage;
    }

    public String getMapping() {
        return currentPage;
    }
}