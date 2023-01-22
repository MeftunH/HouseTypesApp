package service;

import data.House;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;

public interface IHouseService {
    BigDecimal getTotalPriceOfHomes();

    BigDecimal getTotalPriceOfVillas();

    BigDecimal getTotalPriceOfResorts();

    BigDecimal getTotalPriceOfAllTypeHouses();

    OptionalDouble getAverageSquareMetersOfHomes();

    OptionalDouble getAverageSquareMetersOfVillas();

    OptionalDouble getAverageSquareMetersOfResorts();

    OptionalDouble getAverageSquareMetersOfAllTypeHouses();

    List<House> getHomesByFilter(int roomNumber, int saloonNumber);
}
