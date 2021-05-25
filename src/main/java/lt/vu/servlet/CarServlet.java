package lt.vu.servlet;

import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class CarServlet extends HttpServlet {

    @Inject
    CarsDAO carsDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Car> cars = carsDAO.loadAll();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Car car : cars) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("id", car.getId())
                .add("numberplate", car.getNumberplate())
                .add("name", car.getName());
            arrayBuilder.add(objectBuilder);
        }
        JsonArray jsonArray = arrayBuilder.build();
        String jsonString;
        try(Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonArray);
            jsonString = writer.toString();
        }
        out.print(jsonString);
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject jsonObject = reader.readObject();
        Car car = new Car();
        car.setName(jsonObject.getString("name"));
        car.setNumberplate(jsonObject.getString("numberplate"));
        carsDAO.addCar(car);
    }



    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        JsonReader reader = Json.createReader(bufferedReader);
        JsonObject jsonObject = reader.readObject();
        int carId = jsonObject.getInt("id");
        Car car = carsDAO.findOne(carId);
        car.setName(jsonObject.getString("name"));
        car.setNumberplate(jsonObject.getString("numberplate"));
        carsDAO.addCar(car);
    }
}
