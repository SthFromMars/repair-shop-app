package lt.vu.api;

import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@ApplicationScoped
@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
public class CarApi {

    @Inject
    CarsDAO carsDAO;

    @Path("/{carId}")
    @GET
    public Car getCar(@PathParam("carId") int carId) {
        return carsDAO.findOne(carId);
    }

    @Path("/")
    @GET
    public List<Car> getAll() {
        return carsDAO.loadAll();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Car post(Car car) {
        carsDAO.addCar(car);
        return car;
    }


    @Path("/{carId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Car update(Car car, @PathParam("carId") int carId) {
        Car carToUpdate = carsDAO.findOne(carId);
        carToUpdate.setName(car.getName());
        carToUpdate.setNumberplate(car.getNumberplate());
        carsDAO.addCar(car);
        return carToUpdate;
    }
}
