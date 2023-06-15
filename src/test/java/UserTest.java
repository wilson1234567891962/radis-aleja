
import com.co.radis.model.User;
import com.google.gson.Gson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void checkTheUserInformation() {
        String json = "{\n"
                + "  \"estado\": \"AA.\",\n"
                + "  \"mensaje\": \"\",\n"
                + "  \"beneficiarios\": {\n"
                + "    \"12212\": {\n"
                + "      \"id_programacion\": \"AAAA\",\n"
                + "      \"cod_identificacion\": \"AAAA\",\n"
                + "      \"nombre\": \"AAAA\",\n"
                + "      \"id_persona\": \"AAAA\",\n"
                + "      \"edad\": \"14\",\n"
                + "      \"id_beneficiario\": \"AAAA\",\n"
                + "      \"asistencia\": \"0\",\n"
                + "      \"estado\": \"EN AAAA\",\n"
                + "      \"foto\": \"AAAA.png\",\n"
                + "      \"ftfecha\": \"AAAA\",\n"
                + "      \"blqatm\": \"99\"\n"
                + "    }\n"
                + "  },\n"
                + "  \"resultados\": \"1\",\n"
                + "  \"tipo_toma\": \"CONS\",\n"
                + "  \"clave\": \"AAAA\",\n"
                + "  \"consulta\": \"AAAA\",\n"
                + "}";
        User user = new Gson().fromJson(json, User.class);
        assertEquals("", "99");
    }
}
